package br.harlan.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.harlan.api.documents.EmployeesDocument;

public interface EmployeesRepository extends MongoRepository<EmployeesDocument, String> {
	EmployeesDocument findByName(String name);
}