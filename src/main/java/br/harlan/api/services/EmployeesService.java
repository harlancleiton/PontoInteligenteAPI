package br.harlan.api.services;

import java.util.Optional;

import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.entities.EmployeesEntity;

public interface EmployeesService {

	/**
	 * Find Employee by CPF
	 * 
	 * @param cpf
	 * @return Optional<EmployeesEntity>
	 */
	Optional<EmployeesEntity> findByCpf(String cpf);

	/**
	 * Find Employee by Name
	 * 
	 * @param name
	 * @return Optional<EmployeesEntity>
	 */
	Optional<EmployeesEntity> findByName(String name);

	/**
	 * Find Employee by Email
	 * 
	 * @param email
	 * @return Optional<EmployeesEntity>
	 */
	Optional<EmployeesEntity> findByEmail(String email);

	/**
	 * Find Employee by Name or CPF
	 * 
	 * @param name
	 * @param cpf
	 * @return Optional<EmployeesEntity>
	 */
	Optional<EmployeesEntity> findByNameOrCpf(String name, String cpf);

	/**
	 * Find Employee by Name or Email
	 * 
	 * @param name
	 * @param email
	 * @return Optional<EmployeesEntity>
	 */
	Optional<EmployeesEntity> findByNameOrEmail(String name, String email);

	/**
	 * Create Employee
	 * 
	 * @param employee
	 * @return EmployeesEntity
	 */
	EmployeesEntity create(EmployeesEntity employeesEntity);
}