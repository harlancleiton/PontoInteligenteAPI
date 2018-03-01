package br.harlan.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.repositories.CompanyRepository;
import br.harlan.api.services.CompanyService;

@RestController
@RequestMapping("/api/company-registration")
@CrossOrigin(origins = "*")
public class CompanyRegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRegistrationController.class);
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyRepository companyRepository;
	
	
}