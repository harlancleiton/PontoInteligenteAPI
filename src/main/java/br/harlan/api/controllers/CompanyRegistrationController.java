package br.harlan.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.dtos.CompanyRegistrationDto;
import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.repositories.CompanyRepository;
import br.harlan.api.repositories.EmployeesRepository;
import br.harlan.api.response.Response;
import br.harlan.api.services.CompanyService;
import br.harlan.api.services.EmployeesService;

@RestController
@RequestMapping("/api/company-registration")
@CrossOrigin(origins = "*")
public class CompanyRegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRegistrationController.class);

	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private EmployeesRepository employeesRepository;

	public CompanyRegistrationController() {
	}

	public ResponseEntity<Response<CompanyRegistrationDto>> register(
			@Valid @RequestBody CompanyRegistrationDto companyRegistrationDto, BindingResult bindingResult)
			throws NoSuchAlgorithmException {
		LOGGER.info("Cadastrando PJ: {}", companyRegistrationDto.toString());
		Response<CompanyRegistrationDto> response = new Response<CompanyRegistrationDto>();

		validate(companyRegistrationDto, bindingResult);

		CompanyEntity companyEntity = dtoToCompanyEntity(companyRegistrationDto);
		EmployeesEntity employeesEntity = dtoToEmployeesEmtity(companyRegistrationDto, bindingResult);

		return null;
	}

	private void validate(CompanyRegistrationDto companyRegistrationDto, BindingResult bindingResult) {
		companyService.findByCnpj(companyRegistrationDto.getCnpj())
				.ifPresent(company -> bindingResult.addError(new ObjectError("Empresa", "Empresa já existe")));
		employeesService.findByCpf(companyRegistrationDto.getCpf()).ifPresent(
				employees -> bindingResult.addError(new ObjectError("Funcionario", "Funcionario já existe")));
		employeesService.findByEmail(companyRegistrationDto.getEmail())
				.ifPresent(employees -> bindingResult.addError(new ObjectError("Funcionario", "Email já cadastrado")));

	}

	private CompanyEntity dtoToCompanyEntity(CompanyRegistrationDto companyRegistrationDto) {
		// TODO Auto-generated method stub
		return null;
	}

	private EmployeesEntity dtoToEmployeesEmtity(CompanyRegistrationDto companyRegistrationDto,
			BindingResult bindingResult) {
		// TODO Auto-generated method stub
		return null;
	}
}