package br.harlan.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.enums.ProfileEnum;
import br.harlan.api.utils.PasswordUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeesRepositoryTest {
	
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private EmployeesRepository employeesRepository;

	private static final String NAME = "Fulano Exemplo";
	private static final String EMAIL = "example@gmail.com";
	private static final String SENHA = "8431864801";
	private static final String CPF = "27491403297";

	@Before
	public void setUp() throws Exception {
		CompanyEntity companyEntity = companyRepository.save(getCompany());
		employeesRepository.save(getEmployees(companyEntity));
	}

	@After
	public void tearDown() {
		companyRepository.deleteAll();
	}

	@Test
	public void testFindByEmail() {
		EmployeesEntity employeesEntity = employeesRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, employeesEntity.getEmail());
	}
	
	@Test
	public void testFindByCpf() {
		EmployeesEntity employeesEntity = employeesRepository.findByCpf(CPF);
		assertEquals(CPF, employeesEntity.getCpf());
	}
	
	@Test
	public void testFindByCpfOrEmail() {
		EmployeesEntity employeesEntity = employeesRepository.findByNameOrCpf(NAME, CPF);
		assertNotNull(employeesEntity);
	}
	
	@Test
	public void testFindByCpfOrEmailCpfInvalidate() {
		EmployeesEntity employeesEntity = employeesRepository.findByNameOrCpf(NAME, "12345678901");
		assertNotNull(employeesEntity);
	}
	
	@Test
	public void testFindByCpfOrEmailNameInvalidate() {
		EmployeesEntity employeesEntity = employeesRepository.findByNameOrCpf("XYZ", CPF);
		assertNotNull(employeesEntity);
	}
	
	private EmployeesEntity getEmployees(CompanyEntity companyEntity) throws NoSuchAlgorithmException {
		EmployeesEntity employeesEntity = new EmployeesEntity();
		employeesEntity.setName(NAME);
		employeesEntity.setEmail(EMAIL);
		employeesEntity.setProfileEnum(ProfileEnum.ROLE_USER);
		employeesEntity.setPassword(PasswordUtil.generateBCrypt("123456789"));
		employeesEntity.setCpf(CPF);
		employeesEntity.setCompanyEntity(companyEntity);
		return employeesEntity;
	}
	
	private CompanyEntity getCompany() {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setSocialName(CompanyRepositoryTest.SOCIAL_NAME);
		companyEntity.setCnpj(CompanyRepositoryTest.CNPJ);
		return companyEntity;
	}
}
