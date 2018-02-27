package br.harlan.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.harlan.api.entities.PointReleasesEntity;

public interface PointReleasesService {

	/**
	 * Find PointReleases by EmployeeId
	 * 
	 * @param employeeId
	 * @param pageRequest
	 * @return Page<PointReleasesEntity>
	 */
	Page<PointReleasesEntity> findByEmployeeId(Long id, PageRequest pageRequest);
	
	/**
	 * Find PointReleases by Id
	 * 
	 * @param id
	 * @return Optional<PointReleasesEntity>
	 */
	Optional<PointReleasesEntity> findById(Long id);

	/**
	 * Create PointRelease
	 * 
	 * @param pointRelease
	 * @return PointReleasesEntity
	 */
	PointReleasesEntity create(PointReleasesEntity pointRelease);

	/**
	 * Remove PointRelease by Id
	 * 
	 * @param id
	 */
	void delete(Long id);
}