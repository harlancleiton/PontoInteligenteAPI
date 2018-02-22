package br.harlan.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.harlan.api.entities.CompanyEntity;
import br.harlan.api.entities.EmployeesEntity;
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
//			CompanyDocument companyDocument = new CompanyDocument();
//			companyDocument.setCnpj("13578869000160");
//			companyDocument.setSocialName("MRM Construtora");
//			companyDocument = companyRepository.findBySocialName(companyDocument.getSocialName());
//			//companyRepository.save(companyDocument);
//			
//			List<EmployeesDocument> employeesDocuments = new ArrayList<>();
//			EmployeesDocument employeesDocumentsAux = new EmployeesDocument();
//			EmployeesDocument employeesDocumentsAux2 = new EmployeesDocument();
//			employeesDocumentsAux.setName("Harlan");
//			employeesDocumentsAux2.setName("Vanessa");
//			employeesDocuments.add(employeesDocumentsAux);
//			employeesDocuments.add(employeesDocumentsAux2);
//			employeesDocuments.add(employeesDocumentsAux);
//			employeesDocuments.add(employeesDocumentsAux2);
//			employeesDocumentsAux.setCompanyDocument(companyDocument);
//			employeesDocumentsAux2.setCompanyDocument(companyDocument);
//			companyDocument.setEmployees(employeesDocuments);
//			
//			employeesRepository.save(employeesDocumentsAux);
//			employeesRepository.save(employeesDocumentsAux2);
//			companyRepository.save(companyDocument);
		};
	}
}