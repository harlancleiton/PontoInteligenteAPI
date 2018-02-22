package br.harlan.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import br.harlan.api.entities.CompanyEntity;

@Transactional(readOnly = true)
public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {

	CompanyEntity findBySocialName(String socialName);

	CompanyEntity findByCnpj(String cnpj);
}