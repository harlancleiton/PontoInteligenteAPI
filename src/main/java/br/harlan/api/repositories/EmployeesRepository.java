package br.harlan.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import br.harlan.api.entities.EmployeesEntity;

@Transactional(readOnly = true)
public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Long> {
	
	EmployeesEntity findByName(String name);
	
	EmployeesEntity findByEmail(String email);

	EmployeesEntity findByCpf(String cpf);

	EmployeesEntity findByNameOrCpf(String name, String cpf);

	EmployeesEntity findByNameOrEmail(String name, String email);
}