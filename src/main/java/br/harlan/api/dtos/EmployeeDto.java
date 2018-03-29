package br.harlan.api.dtos;

import java.util.Optional;

public class EmployeeDto {
	private Long id;
	private String name;
	private String email;
	private Optional<String> password = Optional.empty();
	private Optional<String> hourValue = Optional.empty();
	private Optional<String> hourPerDay = Optional.empty();
	private Optional<String> hourLaunch = Optional.empty();
}