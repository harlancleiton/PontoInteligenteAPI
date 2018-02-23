package br.harlan.api.services.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.repositories.CompanyRepository;
import br.harlan.api.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Optional<CompanyEntity> findByCnpj(String cnpj) {
		logger.info("Buscando empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(companyRepository.findByCnpj(cnpj));
	}

	@Override
	public CompanyEntity create(CompanyEntity company) {
		logger.info("Persistindo empresa {}", company);
		return companyRepository.save(company);
	}
}