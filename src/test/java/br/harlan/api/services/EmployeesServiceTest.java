package br.harlan.api.services;

import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.jar.Attributes.Name;

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

import br.harlan.api.entities.EmployeesEntity;
import br.harlan.api.repositories.EmployeesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeesServiceTest {

	@MockBean
	private EmployeesRepository employeesRepository;
	@Autowired
	private EmployeesService employeesService;

	private static final String NAME = "Harlan";
	private static final String EMAIL = "Harlan@example.com";
	private static final String CPF = "12357412750";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(employeesRepository.save(Mockito.any(EmployeesEntity.class))).willReturn(new EmployeesEntity());
		BDDMockito.given(employeesRepository.findByName(Mockito.anyString())).willReturn(new EmployeesEntity());
		BDDMockito.given(employeesRepository.findByEmail(Mockito.anyString())).willReturn(new EmployeesEntity());
		BDDMockito.given(employeesRepository.findByCpf(Mockito.anyString())).willReturn(new EmployeesEntity());
		BDDMockito.given(employeesRepository.findByNameOrCpf(Mockito.anyString(), Mockito.anyString())).willReturn(new EmployeesEntity());
		BDDMockito.given(employeesRepository.findByNameOrEmail(Mockito.anyString(), Mockito.anyString())).willReturn(new EmployeesEntity());
	}

	@Test
	public void testFindByName() {
		Optional<EmployeesEntity> employee = employeesService.findByName(NAME);
		assertTrue(employee.isPresent());
	}

	@Test
	public void testFindByEmail() {
		Optional<EmployeesEntity> employee = employeesService.findByEmail(EMAIL);
		assertTrue(employee.isPresent());
	}

	@Test
	public void testFindByCpf() {
		Optional<EmployeesEntity> employee = employeesService.findByCpf(CPF);
		assertTrue(employee.isPresent());
	}

	@Test
	public void testFindByNameOrCpf() {
		Optional<EmployeesEntity> employee = employeesService.findByNameOrCpf(NAME, CPF);
		assertTrue(employee.isPresent());
	}

	@Test
	public void testFindByNameOrEmail() {
		Optional<EmployeesEntity> employee = employeesService.findByNameOrEmail(NAME, EMAIL);
		assertTrue(employee.isPresent());
	}
}