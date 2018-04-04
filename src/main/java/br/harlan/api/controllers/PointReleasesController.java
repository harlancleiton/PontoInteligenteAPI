package br.harlan.api.controllers;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.harlan.api.services.EmployeesService;
import br.harlan.api.services.PointReleasesService;

@RestController
@RequestMapping("/api/point-releases")
@CrossOrigin(origins = "*")
public class PointReleasesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PointReleasesController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	PointReleasesService pointReleasesService;
	@Autowired
	EmployeesService employeesService;
	
	@Value("@{pagination.amount_per_page}")
	private int amountPerPage;
	
	public PointReleasesController() {
	}
	
	
}