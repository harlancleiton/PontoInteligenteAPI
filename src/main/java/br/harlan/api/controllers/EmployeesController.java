package br.harlan.api.controllers;

import java.security.NoSuchAlgorithmException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.dtos.EmployeeDto;
import br.harlan.api.response.Response;
import br.harlan.api.services.EmployeesService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesController.class);

	@Autowired
	private EmployeesService employeesService;

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<EmployeeDto>> update(@PathVariable("id") Long id, EmployeeDto employeeDto, BindingResult bindingResult)
			throws NoSuchAlgorithmException {
		LOGGER.info("Atualizando funcionário: {}", employeeDto.toString());
		return null;
	}
}