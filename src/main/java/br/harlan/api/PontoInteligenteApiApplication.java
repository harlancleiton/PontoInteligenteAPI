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
			testeRepository.deleteAll();
			
			testeRepository.save(new TesteDocument("Harlan"));
			testeRepository.save(new TesteDocument("Vanessa"));
			testeRepository.save(new TesteDocument("Maria"));
			
			System.out.println("Lista todos com o findAll():");
			testeRepository.findAll().forEach(System.out::println);
			
			System.out.println("findByName: 'Harlan'");
			System.out.println(testeRepository.findByName("Harlan"));
			
		};
	}
}