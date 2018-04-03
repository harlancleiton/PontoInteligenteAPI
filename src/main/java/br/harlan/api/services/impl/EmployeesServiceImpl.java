package br.harlan.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.repositories.EmployeesRepository;
import br.harlan.api.services.EmployeesService;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private EmployeesRepository employeesRepository;

	@Override
	public Optional<EmployeesEntity> findById(Long id) {
		LOGGER.info("Buscando funcionário por CPF {}", id);
		return Optional.ofNullable(employeesRepository.findById(id));
	}
	
	@Override
	public Optional<EmployeesEntity> findByCpf(String cpf) {
		LOGGER.info("Buscando funcionário por CPF {}", cpf);
		return Optional.ofNullable(employeesRepository.findByCpf(cpf));
	}

	@Override
	public Optional<EmployeesEntity> findByName(String name) {
		LOGGER.info("Buscando funcionário por Nome {}", name);
		return Optional.ofNullable(employeesRepository.findByName(name));
	}

	@Override
	public Optional<EmployeesEntity> findByEmail(String email) {
		LOGGER.info("Buscando funcionário por Email {}", email);
		return Optional.ofNullable(employeesRepository.findByEmail(email));
	}

	@Override
	public Optional<EmployeesEntity> findByNameOrCpf(String name, String cpf) {
		LOGGER.info("Buscando funcionário por Nome {} ou CPF {}", name, cpf);
		return Optional.ofNullable(employeesRepository.findByNameOrCpf(name, cpf));
	}

	@Override
	public Optional<EmployeesEntity> findByNameOrEmail(String name, String email) {
		LOGGER.info("Buscando funcionário por Nome {} ou Email {}", name, email);
		return Optional.ofNullable(employeesRepository.findByNameOrEmail(name, email));
	}

	@Override
	public EmployeesEntity create(EmployeesEntity employees) {
		LOGGER.info("Persistindo funcionário {}", employees);
		return employeesRepository.save(employees);
	}
}