package br.harlan.api.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mysql.jdbc.log.Log;

import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.services.CompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyService companyService;

	private static final String URL_FIND_COMPANY_CNPJ = "/api/company/cnpj/";
	private static final Long ID = Long.valueOf(1);
	private static final String CNPJ = "89116521000112";
	private static final String SOCIAL_NAME = "SoftFacHW";

	@Test
	public void testFindCompanyByCnpjInvalid() throws Exception {
		BDDMockito.given(companyService.findByCnpj(Mockito.anyString())).willReturn(Optional.empty());
		mockMvc.perform(MockMvcRequestBuilders.get(URL_FIND_COMPANY_CNPJ + CNPJ).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Empresa não encontrada para o CNPJ " + CNPJ + "."));
	}

	@Test
	public void testFindCompanyByCnpjValid() throws Exception {
		BDDMockito.given(companyService.findByCnpj(Mockito.anyString())).willReturn(Optional.of(getCompany()));
		mockMvc.perform(MockMvcRequestBuilders.get(URL_FIND_COMPANY_CNPJ + CNPJ).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.socialName", equalTo(SOCIAL_NAME)))
				.andExpect(jsonPath("$.data.cnpj", equalTo(CNPJ))).andExpect(jsonPath("$.errors").isEmpty());
	}

	private CompanyEntity getCompany() {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setId(ID);
		companyEntity.setCnpj(CNPJ);
		companyEntity.setSocialName(SOCIAL_NAME);
		return companyEntity;
	}
}