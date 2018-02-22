package br.harlan.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.harlan.api.documents.CompanyDocument;
import br.harlan.api.documents.EmployeesDocument;
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
			CompanyDocument companyDocument = new CompanyDocument();
			companyDocument.setCnpj("13578869000160");
			companyDocument.setSocialName("MRM Construtora");
			List<EmployeesDocument> employeesDocuments = new ArrayList<>();
			EmployeesDocument employeesDocumentsAux = new EmployeesDocument();
			employeesDocumentsAux.setName("Harlan");
			employeesDocuments.add(employeesDocumentsAux);
			employeesDocuments.add(employeesDocumentsAux);
			employeesDocuments.add(employeesDocumentsAux);
			companyDocument.setEmployees(employeesDocuments);
			employeesRepository.save(employeesDocumentsAux);
			companyRepository.save(companyDocument);
		};
	}
}