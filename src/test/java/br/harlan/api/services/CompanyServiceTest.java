package br.harlan.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.repositories.CompanyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceTest {

	@MockBean
	private CompanyRepository companyRepository;
	@Autowired(required = true)
	private CompanyService companyService;

	private static final String CNPJ = "13719473100074";

	@Before
	public void setUp() throws Exception {
		// Qualquer coisa que que esse metodo receber, retorne uma new CompanyEntity
		BDDMockito.given(companyRepository.findByCnpj(Mockito.anyString())).willReturn(new CompanyEntity());
		BDDMockito.given(companyRepository.save(Mockito.any(CompanyEntity.class))).willReturn(new CompanyEntity());
	}

	@Test
	public void testFindCompanyByCnpj() {
		Optional<CompanyEntity> company = companyService.findByCnpj(CNPJ);
		assertTrue(company.isPresent());
	}

	@Test
	public void testCreateCompany() {
		CompanyEntity company = companyService.create(new CompanyEntity());
		assertNotNull(company);
	}
}