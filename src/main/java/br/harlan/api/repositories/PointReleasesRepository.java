package br.harlan.api.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import br.harlan.api.entities.PointReleasesEntity;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name="")
})
public interface PointReleasesRepository extends MongoRepository<PointReleasesEntity, String> {
	
}