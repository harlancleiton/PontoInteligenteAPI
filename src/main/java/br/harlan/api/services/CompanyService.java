package br.harlan.api.services;

import java.util.Optional;

import br.harlan.api.entities.CompanyEntity;

public interface CompanyService {

	/**
	 * Find Company by CNPJ
	 * 
	 *  @param cnpj
	 *  @return Optional<CompanyEntity>
	 */
	Optional<CompanyEntity> findByCnpj(String cnpj);
	
	/**
	 * Create Company
	 * 
	 * @param company
	 * @return CompanyEntity
	 */
	CompanyEntity create(CompanyEntity company);
}