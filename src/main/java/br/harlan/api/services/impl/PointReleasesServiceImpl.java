package br.harlan.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.harlan.api.entities.PointReleasesEntity;
import br.harlan.api.repositories.PointReleasesRepository;
import br.harlan.api.services.PointReleasesService;

@Service
public class PointReleasesServiceImpl implements PointReleasesService {
	
	private static final Logger logger = LoggerFactory.getLogger(PointReleasesServiceImpl.class);
	
	@Autowired
	PointReleasesRepository pointReleasesRepository;

	@Override
	public Page<PointReleasesEntity> findByEmployeeId(Long id, PageRequest pageRequest) {
		logger.info("Buscando lançamentos para o funcionario ID {}", id);
		return pointReleasesRepository.findByEmployeesId(id, pageRequest);
	}

	@Override
	public Optional<PointReleasesEntity> findById(Long id) {
		logger.info("Buscando lançamento pelo ID {}", id);
		return Optional.ofNullable(pointReleasesRepository.findOne(id));
	}

	@Override
	public PointReleasesEntity create(PointReleasesEntity pointRelease) {
		logger.info("Persistindo o lançamento {}", pointRelease);
		return pointReleasesRepository.save(pointRelease);
	}

	@Override
	public void delete(Long id) {
		logger.info("Removendo o lançamento ID {}", id);
		pointReleasesRepository.delete(id);
	}
}