package br.harlan.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.harlan.api.entities.PointReleasesEntity;

public interface PointReleasesRepository extends MongoRepository<PointReleasesEntity, String> {
	
}