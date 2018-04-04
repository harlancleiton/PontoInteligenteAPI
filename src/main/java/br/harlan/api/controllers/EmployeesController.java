package br.harlan.api.controllers;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.dtos.EmployeeDto;
import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.response.Response;
import br.harlan.api.services.EmployeesService;
import br.harlan.api.utils.PasswordUtil;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesController.class);

	@Autowired
	private EmployeesService employeesService;

	/**
	 * Atualiza os dados de um funcionário por ID.
	 * 
	 * @param id
	 * @param employeeDto
	 * @param bindingResult
	 * @return ResponseEntity<Response<EmployeeDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<EmployeeDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) throws NoSuchAlgorithmException {
		LOGGER.info("Atualizando funcionário: {}", employeeDto.toString());
		Response<EmployeeDto> response = new Response<EmployeeDto>();
		Optional<EmployeesEntity> eOptional = employeesService.findById(id);

		if (!eOptional.isPresent()) {
			bindingResult.addError(new ObjectError("Funcionario", "Funcionário não encontrado."));
		}

		updateEmployeeEntity(eOptional.get(), employeeDto, bindingResult);

		if (bindingResult.hasErrors()) {
			LOGGER.error("Erro ao validar funcionário: {}", bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		employeesService.create(eOptional.get());
		response.setData(entityToEmployeeDto(eOptional.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Popula o EmployeesEntity de acordo com os dados passados pelo EmployeeDto.
	 * 
	 * @param employeesEntity
	 * @param employeeDto
	 * @param bindingResult
	 * @throws NoSuchAlgorithmException
	 */
	private void updateEmployeeEntity(EmployeesEntity employeesEntity, EmployeeDto employeeDto,
			BindingResult bindingResult) throws NoSuchAlgorithmException {
		employeesEntity.setName(employeeDto.getName());
		if (!employeesEntity.getEmail().equals(employeeDto.getEmail())) {
			employeesService.findByEmail(employeeDto.getEmail())
					.ifPresent(employee -> bindingResult.addError(new ObjectError("Email", "Email já existente.")));
			employeesEntity.setEmail(employeeDto.getEmail());
		}
		employeesEntity.setHourLaunch(null);
		employeeDto.getHourLaunch().ifPresent(hourLaunch -> employeesEntity.setHourLaunch(Float.valueOf(hourLaunch)));
		employeesEntity.setHourPerDay(null);
		employeeDto.getHourPerDay().ifPresent(hourPerDay -> employeesEntity.setHourLaunch(Float.valueOf(hourPerDay)));
		employeesEntity.setHourValue(null);
		employeeDto.getHourValue().ifPresent(hourValue -> employeesEntity.setHourValue(new BigDecimal(hourValue)));

		if (employeeDto.getPassword().isPresent()) {
			employeesEntity.setPassword(PasswordUtil.generateBCrypt(employeeDto.getPassword().get()));
		}
	}

	/**
	 * Retorna um EmployeeDto com os dados do funcionário.
	 * 
	 * @param employeesEntity
	 * @return
	 */
	private EmployeeDto entityToEmployeeDto(EmployeesEntity employeesEntity) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employeesEntity.getId());
		employeeDto.setName(employeesEntity.getName());
		employeeDto.setEmail(employeesEntity.getEmail());
		employeesEntity.getHourLaunchOpt()
				.ifPresent(hourLaunch -> employeeDto.setHourLaunch(Optional.of(Float.toString(hourLaunch))));
		employeesEntity.getHourPerDayOpt()
				.ifPresent(hourPerDay -> employeeDto.setHourPerDay(Optional.of(Float.toString(hourPerDay))));
		employeesEntity.getHourValueOpt()
				.ifPresent(hourValue -> employeeDto.setHourValue(Optional.of(hourValue.toString())));
		return employeeDto;
	}
}