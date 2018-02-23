package br.harlan.api.repositories;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.harlan.api.entities.CompanyEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepositoryTest {

	@Autowired
	private CompanyRepository companyRepository;

	public static final String CNPJ = "13578869100160";
	public static final String SOCIAL_NAME = "Example Company";
	@Before
	public void setUp() throws Exception {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setSocialName(SOCIAL_NAME);
		companyEntity.setCnpj(CNPJ);
		companyRepository.save(companyEntity);
	}

	@After
	public void tearDown() {
		companyRepository.deleteAll();
	}

	@Test
	public void testFindByCnpj() {
		CompanyEntity companyEntity = companyRepository.findByCnpj(CNPJ);
		assertEquals(CNPJ, companyEntity.getCnpj());
	}
}