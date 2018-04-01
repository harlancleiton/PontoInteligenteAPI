package br.harlan.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.dtos.CompanyDto;
import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.response.Response;
import br.harlan.api.services.CompanyService;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRegistrationController.class);

	@Autowired
	CompanyService companyService;

	public CompanyController() {
	}

	/**
	 * Busca uma empresa para determinado CNPJ
	 * 
	 * @param cnpj
	 * @return
	 */
	@GetMapping(value = "cnpj/{cnpj}")
	public ResponseEntity<Response<CompanyDto>> findByCnpj(@PathVariable("cnpj") String cnpj) {
		LOGGER.info("Buscando empresa por CNPJ: {}", cnpj);
		Response<CompanyDto> response = new Response<CompanyDto>();
		Optional<CompanyEntity> cOptional = companyService.findByCnpj(cnpj);
		if (!cOptional.isPresent()) {
			LOGGER.info("Empresa não encontrada para o CNPJ: {} ", cnpj);
			response.getErrors().add("Empresa não encontrada para o CNPJ " + cnpj + ".");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(entityToDto(cOptional.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Popula um DTO com os dados de uma empresa
	 * 
	 * @param companyEntity
	 * @return
	 */
	private CompanyDto entityToDto(CompanyEntity companyEntity) {
		CompanyDto companyDto = new CompanyDto();
		companyDto.setId(companyEntity.getId());
		companyDto.setCnpj(companyEntity.getCnpj());
		companyDto.setSocialName(companyEntity.getSocialName());
		return companyDto;
	}
}