package br.harlan.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.harlan.api.documents.TesteDocument;

public interface TesteRepository extends MongoRepository<TesteDocument, String> {
	TesteDocument findByName(String name);
}