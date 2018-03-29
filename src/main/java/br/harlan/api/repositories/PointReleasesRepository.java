package br.harlan.api.repositories;

import java.util.List;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import br.harlan.api.entities.PointReleasesEntity;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "PointReleasesRepository.findByEmployeesEntityId", 
				query = "select release from PointReleasesRepository release where release.employees.id = :employeesId") 
		})
public interface PointReleasesRepository extends JpaRepository<PointReleasesEntity, Long> {
	
	List<PointReleasesEntity> findByEmployeesId(@Param("employeesId") Long employeesId);

	Page<PointReleasesEntity> findByEmployeesId(@Param("employeesId") Long employeesId, Pageable pageable);
}