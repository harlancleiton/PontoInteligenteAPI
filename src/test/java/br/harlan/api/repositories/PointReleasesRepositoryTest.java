package br.harlan.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

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
import br.harlan.api.entities.PointReleasesEntity;
import br.harlan.api.enums.ProfileEnum;
import br.harlan.api.enums.TypeReleaseEnum;
import br.harlan.api.utils.PasswordUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PointReleasesRepositoryTest {

	@Autowired
	private PointReleasesRepository pointReleasesRepository;
	@Autowired
	private EmployeesRepository employeesRepository;
	@Autowired
	CompanyRepository companyRepository;
	private Long employeesId;

	@Before
	public void setUp() throws Exception {
		CompanyEntity companyEntity = companyRepository.save(getCompany());

		EmployeesEntity employeesEntity = employeesRepository.save(getEmployee(companyEntity));
		employeesId = employeesEntity.getId();

		pointReleasesRepository.save(getPointReleases(employeesEntity));
		pointReleasesRepository.save(getPointReleases(employeesEntity));
	}

	@After
	public void tearDown() throws Exception {
		companyRepository.deleteAll();
	}

	private CompanyEntity getCompany() {
		CompanyEntity company = new CompanyEntity();
		company.setSocialName("Empresa de exemplo");
		company.setCnpj("51463645000100");
		return company;
	}

	private PointReleasesEntity getPointReleases(EmployeesEntity employee) {
		PointReleasesEntity pointRelease = new PointReleasesEntity();
		pointRelease.setReleaseDate(new Date());
		pointRelease.setTypeReleaseEnum(TypeReleaseEnum.START_WORK);
		pointRelease.setEmployeesEntity(employee);
		return pointRelease;
	}

	private EmployeesEntity getEmployee(CompanyEntity company) throws NoSuchAlgorithmException {
		EmployeesEntity employeesEntity = new EmployeesEntity();
		employeesEntity.setName("Fulano de Tal");
		employeesEntity.setProfileEnum(ProfileEnum.ROLE_USER);
		employeesEntity.setPassword(PasswordUtil.generateBCrypt("123456"));
		employeesEntity.setCpf("24291173474");
		employeesEntity.setEmail("email@email.com");
		employeesEntity.setCompanyEntity(company);
		return employeesEntity;
	}

	@Test
	public void testFindPointReleaseByEmployeesId() {
		List<PointReleasesEntity> pointReleases = pointReleasesRepository.findByEmployeesEntityId(employeesId);

		assertEquals(2, pointReleases.size());
	}
}