package br.harlan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.harlan.api.repositories.CompanyRepository;
import br.harlan.api.repositories.EmployeesRepository;

@SpringBootApplication
public class PontoInteligenteApiApplication {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	EmployeesRepository employeesRepository;

	public static void main(String[] args) {
		SpringApplication.run(PontoInteligenteApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
//			CompanyEntity companyEntity = new CompanyEntity();
//			companyEntity.setCnpj("13578869000160");
//			companyEntity.setSocialName("MRM Construtora");
//			companyEntity = companyRepository.findBySocialName(companyEntity.getSocialName());
//			companyRepository.save(companyEntity);
		};
	}
}