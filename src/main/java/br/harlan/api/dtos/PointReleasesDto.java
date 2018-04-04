package br.harlan.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.NotEmpty;

public class PointReleasesDto {
	private Optional<Long> id = Optional.empty();
	@NotEmpty(message="Data não pode ser vazia.")
	private String date;
	private String typeRelease;
	private String description;
	private String location;
	private Long employeeId;
	
	public PointReleasesDto() {
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTypeRelease() {
		return typeRelease;
	}

	public void setTypeRelease(String typeRelease) {
		this.typeRelease = typeRelease;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "PointReleasesDto [id=" + id + ", date=" + date + ", typeRelease=" + typeRelease + ", description="
				+ description + ", location=" + location + ", employeeId=" + employeeId + "]";
	}
}