package br.harlan.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.harlan.api.entities.CompanyEntity;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {
	CompanyEntity findBySocialName(String socialName);
	CompanyEntity findByCnpj(String cnpj);
}