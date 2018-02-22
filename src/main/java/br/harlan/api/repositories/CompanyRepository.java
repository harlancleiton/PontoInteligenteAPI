package br.harlan.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.harlan.api.documents.CompanyDocument;

public interface CompanyRepository extends MongoRepository<CompanyDocument, String> {
	CompanyDocument findBySocialName(String socialName);
	CompanyDocument findByCnpj(String cnpj);
}