package br.harlan.api.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.harlan.api.enums.TypeReleaseEnum;

//@Document(collection = "point_releases")
public class PointReleasesEntity {

	//@Id
	private String id;
	private Date releaseDate;
	private String description;
	private String location;
	private Date creationDate;
	private Date updateDate;
	private TypeReleaseEnum typeReleaseEnum;
	//@DBRef
	private EmployeesEntity employeesDocument;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public TypeReleaseEnum getTypeReleaseEnum() {
		return typeReleaseEnum;
	}

	public void setTypeReleaseEnum(TypeReleaseEnum typeReleaseEnum) {
		this.typeReleaseEnum = typeReleaseEnum;
	}

	public EmployeesEntity getEmployeesDocument() {
		return employeesDocument;
	}

	public void setEmployeesDocument(EmployeesEntity employeesDocument) {
		this.employeesDocument = employeesDocument;
	}

	@Override
	public String toString() {
		return "PointReleases [id=" + id + ", releaseDate=" + releaseDate + ", description=" + description
				+ ", location=" + location + ", creationDate=" + creationDate + ", updateDate=" + updateDate
				+ ", typeReleaseEnum=" + typeReleaseEnum + ", employeesDocument=" + employeesDocument + "]";
	}
}