package br.harlan.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import br.harlan.api.entities.EmployeesEntity;

@Transactional(readOnly = true)
public interface EmployeesRepository extends MongoRepository<EmployeesEntity, String> {
	
	EmployeesEntity findByName(String name);

	EmployeesEntity findByCpf(String cpf);

	EmployeesEntity findByNameOrCpf(String name, String cpf);

	EmployeesEntity findByNameOrEmail(String name, String email);
}