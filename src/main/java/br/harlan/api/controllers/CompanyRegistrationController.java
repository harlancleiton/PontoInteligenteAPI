package br.harlan.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.dtos.CompanyRegistrationDto;
import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.enums.ProfileEnum;
import br.harlan.api.repositories.CompanyRepository;
import br.harlan.api.repositories.EmployeesRepository;
import br.harlan.api.response.Response;
import br.harlan.api.services.CompanyService;
import br.harlan.api.services.EmployeesService;
import br.harlan.api.utils.PasswordUtil;

@RestController
@RequestMapping("/api/company-registration")
@CrossOrigin(origins = "*")
public class CompanyRegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRegistrationController.class);

	@Autowired
	private CompanyService companyService;
	// @Autowired
	// private CompanyRepository companyRepository;
	@Autowired
	private EmployeesService employeesService;
	// @Autowired
	// private EmployeesRepository employeesRepository;

	public CompanyRegistrationController() {
	}

	/**
	 * 
	 * 
	 * @param companyRegistrationDto
	 * @param bindingResult
	 * @return ResponseEntity<Response<CompanyRegistrationDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CompanyRegistrationDto>> register(
			@Valid @RequestBody CompanyRegistrationDto companyRegistrationDto, BindingResult bindingResult)
			throws NoSuchAlgorithmException {
		LOGGER.info("Cadastrando PJ: {}", companyRegistrationDto.toString());
		Response<CompanyRegistrationDto> response = new Response<CompanyRegistrationDto>();

		validate(companyRegistrationDto, bindingResult);

		CompanyEntity companyEntity = dtoToCompanyEntity(companyRegistrationDto);
		EmployeesEntity employeesEntity = dtoToEmployeesEntity(companyRegistrationDto, bindingResult);

		if (bindingResult.hasErrors()) {
			LOGGER.error("Erro ao validar dados do Cadastro PJ: {}", bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		companyService.create(companyEntity);
		employeesEntity.setCompanyEntity(companyEntity);
		employeesService.create(employeesEntity);

		response.setData(entityToCompanyRegistrionDto(employeesEntity));
		return ResponseEntity.ok(response);
	}

	/**
	 * Verifica se já existe no banco de dados o CNPJ ou CPF informado
	 * 
	 * @param companyRegistrationDto
	 * @param bindingResult
	 */
	private void validate(CompanyRegistrationDto companyRegistrationDto, BindingResult bindingResult) {
		companyService.findByCnpj(companyRegistrationDto.getCnpj())
				.ifPresent(company -> bindingResult.addError(new ObjectError("Empresa", "Empresa já cadastrada.")));
		employeesService.findByCpf(companyRegistrationDto.getCpf()).ifPresent(
				employees -> bindingResult.addError(new ObjectError("Funcionario", "Funcionario já cadastrado.")));
		employeesService.findByEmail(companyRegistrationDto.getEmail())
				.ifPresent(employees -> bindingResult.addError(new ObjectError("Funcionario", "Email já cadastrado.")));

	}

	/**
	 * Converte os dados do DTO para CompanyEntity
	 * 
	 * @param companyRegistrationDto
	 * @return CompanyEntity
	 */
	private CompanyEntity dtoToCompanyEntity(CompanyRegistrationDto companyRegistrationDto) {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setCnpj(companyRegistrationDto.getCnpj());
		companyEntity.setSocialName(companyRegistrationDto.getSocialName());
		return companyEntity;
	}

	/**
	 * Converte os dados do DTO para EmployeeEntity
	 * 
	 * @param companyRegistrationDto
	 * @param bindingResult
	 * @return employeesEntity
	 * @throws NoSuchAlgorithmException
	 */
	private EmployeesEntity dtoToEmployeesEntity(CompanyRegistrationDto companyRegistrationDto,
			BindingResult bindingResult) throws NoSuchAlgorithmException {
		EmployeesEntity employeesEntity = new EmployeesEntity();
		employeesEntity.setName(companyRegistrationDto.getName());
		employeesEntity.setEmail(companyRegistrationDto.getEmail());
		employeesEntity.setCpf(companyRegistrationDto.getCpf());
		employeesEntity.setProfileEnum(ProfileEnum.ROLE_ADMIN);
		employeesEntity.setPassword(PasswordUtil.generateBCrypt(companyRegistrationDto.getPassword()));
		return employeesEntity;
	}

	/**
	 * Popula o DTO de cadastro com os dados do funcionário e empresa
	 * 
	 * @param employeesEntity
	 * @return companyRegistrationDto
	 */
	private CompanyRegistrationDto entityToCompanyRegistrionDto(EmployeesEntity employeesEntity) {
		CompanyRegistrationDto companyRegistrationDto = new CompanyRegistrationDto();
		companyRegistrationDto.setId(employeesEntity.getId());
		companyRegistrationDto.setName(employeesEntity.getName());
		companyRegistrationDto.setEmail(employeesEntity.getEmail());
		companyRegistrationDto.setCpf(employeesEntity.getCpf());
		companyRegistrationDto.setCnpj(employeesEntity.getCompanyEntity().getCnpj());
		companyRegistrationDto.setSocialName(employeesEntity.getCompanyEntity().getSocialName());
		return companyRegistrationDto;
	}
}