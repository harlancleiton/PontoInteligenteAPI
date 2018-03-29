package br.harlan.api.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.harlan.api.enums.TypeReleaseEnum;

@Entity
@Table(name = "point_releases")
public class PointReleasesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "release_date", nullable = false)
	private Date releaseDate;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "location", nullable = true)
	private String location;

	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	@Column(name = "update_date", nullable = false)
	private Date updateDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_release", nullable = false)
	private TypeReleaseEnum typeReleaseEnum;

	@ManyToOne(fetch = FetchType.EAGER)
	private EmployeesEntity employees;

	@PrePersist
	public void prePersist() {
		final Date currentDate = new Date();
		creationDate = currentDate;
		updateDate = currentDate;
	}

	@PreUpdate
	public void preUpdate() {
		final Date currentDate = new Date();
		updateDate = currentDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public EmployeesEntity getEmployeesEntity() {
		return employees;
	}

	public void setEmployeesEntity(EmployeesEntity employeesEntity) {
		this.employees = employeesEntity;
	}

	@Override
	public String toString() {
		return "PointReleasesEntity [id=" + id + ", releaseDate=" + releaseDate + ", description=" + description
				+ ", location=" + location + ", creationDate=" + creationDate + ", updateDate=" + updateDate
				+ ", typeReleaseEnum=" + typeReleaseEnum + ", employeesEntity=" + employees + "]";
	}
}