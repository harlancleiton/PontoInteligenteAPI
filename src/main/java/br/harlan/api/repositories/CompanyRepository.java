package br.harlan.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import br.harlan.api.entities.CompanyEntity;

@Transactional(readOnly = true)
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

	CompanyEntity findBySocialName(String socialName);

	CompanyEntity findByCnpj(String cnpj);
}