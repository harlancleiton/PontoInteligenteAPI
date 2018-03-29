package br.harlan.api.controllers;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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

import br.harlan.api.dtos.EmployeeRegistrationDto;
import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.enums.ProfileEnum;
import br.harlan.api.response.Response;
import br.harlan.api.services.CompanyService;
import br.harlan.api.services.EmployeesService;
import br.harlan.api.utils.PasswordUtil;

@RestController
@RequestMapping("/api/employee-registration")
@CrossOrigin(origins = "*")
public class EmployeesRegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesRegistrationController.class);

	@Autowired
	private CompanyService companyService;
	@Autowired
	private EmployeesService employeesService;

	public EmployeesRegistrationController() {
	}

	@PostMapping
	public ResponseEntity<Response<EmployeeRegistrationDto>> register(
			@Valid @RequestBody EmployeeRegistrationDto employeeRegistrationDto, BindingResult bindingResult)
			throws NoSuchAlgorithmException {
		LOGGER.info("Cadastrado funcionário: {}", employeeRegistrationDto.toString());
		Response<EmployeeRegistrationDto> response = new Response<EmployeeRegistrationDto>();

		validate(employeeRegistrationDto, bindingResult);
		EmployeesEntity employeesEntity = dtoToEmployeeEntity(employeeRegistrationDto);

		if (bindingResult.hasErrors()) {
			LOGGER.error("Erro ao validar dados de cadastro do funcionário:", bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<CompanyEntity> companyEntity = companyService.findByCnpj(employeeRegistrationDto.getCnpj());
		companyEntity.ifPresent(company -> employeesEntity.setCompanyEntity(company));
		employeesService.create(employeesEntity);

		response.setData(employeeEntityToDto(employeesEntity));
		return ResponseEntity.ok(response);
	}

	/**
	 * Converte os dados do DTO para EmployeesEntity
	 * 
	 * @param employeeRegistrationDto
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public EmployeesEntity dtoToEmployeeEntity(EmployeeRegistrationDto employeeRegistrationDto)
			throws NoSuchAlgorithmException {
		EmployeesEntity employeesEntity = new EmployeesEntity();
		employeesEntity.setCpf(employeeRegistrationDto.getCpf());
		employeesEntity.setName(employeeRegistrationDto.getName());
		employeesEntity.setEmail(employeeRegistrationDto.getEmail());
		employeesEntity.setProfileEnum(ProfileEnum.ROLE_USER);
		employeesEntity.setPassword(PasswordUtil.generateBCrypt(employeeRegistrationDto.getPassword()));
		employeeRegistrationDto.getHourLaunch().ifPresent(aux -> employeesEntity.setHourLaunch(Float.valueOf(aux)));
		employeeRegistrationDto.getHourPerDay().ifPresent(aux -> employeesEntity.setHourPerDay(Float.valueOf(aux)));
		employeeRegistrationDto.getHourValue().ifPresent(aux -> employeesEntity.setHourValue(new BigDecimal(aux)));
		return employeesEntity;
	}

	/**
	 * Verifica se a empresa já foi cadastrada e se o funcionário não existe na base
	 * de dados.
	 * 
	 * @param employeeRegistrationDto
	 * @param bindingResult
	 */
	private void validate(EmployeeRegistrationDto employeeRegistrationDto, BindingResult bindingResult) {
		if (!companyService.findByCnpj(employeeRegistrationDto.getCnpj()).isPresent()) {
			bindingResult.addError(new ObjectError("Company", "Empresa não cadastrada."));
		}

		employeesService.findByCpf(employeeRegistrationDto.getCpf())
				.ifPresent(employees -> bindingResult.addError(new ObjectError("Employee", "CPF já cadastrado.")));

		employeesService.findByEmail(employeeRegistrationDto.getEmail())
				.ifPresent(employees -> bindingResult.addError(new ObjectError("Employee", "Email já cadastrado.")));
	}

	private EmployeeRegistrationDto employeeEntityToDto(EmployeesEntity employeesEntity) {
		EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto();
		employeeRegistrationDto.setId(employeesEntity.getId());
		employeeRegistrationDto.setName(employeesEntity.getName());
		employeeRegistrationDto.setEmail(employeesEntity.getEmail());
		employeeRegistrationDto.setCpf(employeesEntity.getCpf());
		employeeRegistrationDto.setCnpj(employeesEntity.getCompanyEntity().getCnpj());
		employeesEntity.getHourLaunchOpt()
				.ifPresent(aux -> employeeRegistrationDto.setHourLaunch(Optional.of(Float.toString(aux))));
		employeesEntity.getHourPerDayOpt()
				.ifPresent(aux -> employeeRegistrationDto.setHourPerDay(Optional.of(Float.toString(aux))));
		employeesEntity.getHourValueOpt()
				.ifPresent(aux -> employeeRegistrationDto.setHourValue(Optional.of(aux.toString())));
		return employeeRegistrationDto;
	}
}