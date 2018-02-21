package br.harlan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.harlan.api.documents.TesteDocument;
import br.harlan.api.repositories.TesteRepository;

@SpringBootApplication
public class PontoInteligenteApiApplication {
	
	@Autowired
	private TesteRepository testeRepository;

	public static void main(String[] args) {
		SpringApplication.run(PontoInteligenteApiApplication.class, args);
	}
	
	@Bean 
	public CommandLineRunner commandLineRunner() {
		return args -> {
			
		};
	}
}