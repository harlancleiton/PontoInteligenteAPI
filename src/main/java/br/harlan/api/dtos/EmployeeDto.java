package br.harlan.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class EmployeeDto {
	
	private Long id;
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 150, message = "Nome deve conter entre 3 e 150 caracteres.")
	private String name;
	@NotEmpty(message = "Email não pode ser vazio.")
	@Email(message = "Email inválido.")
	@Length(min = 8, max = 180, message = "Email deve conter entre 8 e 180 caracteres.")
	private String email;
	private Optional<String> password = Optional.empty();
	private Optional<String> hourValue = Optional.empty();
	private Optional<String> hourPerDay = Optional.empty();
	private Optional<String> hourLaunch = Optional.empty();
	
	public EmployeeDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<String> getPassword() {
		return password;
	}

	public void setPassword(Optional<String> password) {
		this.password = password;
	}

	public Optional<String> getHourValue() {
		return hourValue;
	}

	public void setHourValue(Optional<String> hourValue) {
		this.hourValue = hourValue;
	}

	public Optional<String> getHourPerDay() {
		return hourPerDay;
	}

	public void setHourPerDay(Optional<String> hourPerDay) {
		this.hourPerDay = hourPerDay;
	}

	public Optional<String> getHourLaunch() {
		return hourLaunch;
	}

	public void setHourLaunch(Optional<String> hourLaunch) {
		this.hourLaunch = hourLaunch;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", email=" + email + ", hourValue=" + hourValue
				+ ", hourPerDay=" + hourPerDay + ", hourLaunch=" + hourLaunch + "]";
	}
}