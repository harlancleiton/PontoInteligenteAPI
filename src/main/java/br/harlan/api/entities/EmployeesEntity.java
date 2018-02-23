package br.harlan.api.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import br.harlan.api.enums.ProfileEnum;

@Entity
@Table(name = "employees")
public class EmployeesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "hour_value", nullable = true)
	private BigDecimal hourValue;

	@Column(name = "hour_per_day", nullable = true)
	private Float hourPerDay;

	@Column(name = "hour_lunch", nullable = true)
	private Float hourLunch;

	@Enumerated(EnumType.STRING)
	@Column(name = "profile", nullable = false)
	private ProfileEnum profileEnum;

	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	@Column(name = "update_date", nullable = false)
	private Date updateDate;

	@OneToMany(mappedBy = "employeesEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PointReleasesEntity> pointReleases;

	@ManyToOne(fetch = FetchType.EAGER)
	private CompanyEntity companyEntity;

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

	public EmployeesEntity() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getHourValue() {
		return hourValue;
	}

	@Transient
	public Optional<BigDecimal> getHourValueOpt() {
		return Optional.ofNullable(hourValue);
	}

	public void setHourValue(BigDecimal hourValue) {
		this.hourValue = hourValue;
	}

	public Float getHourPerDay() {
		return hourPerDay;
	}

	@Transient
	public Optional<Float> getHourPerDayOpt() {
		return Optional.ofNullable(hourPerDay);
	}

	public void setHourPerDay(Float hourPerDay) {
		this.hourPerDay = hourPerDay;
	}

	public Float getHourLunch() {
		return hourLunch;
	}

	public void setHourLunch(Float hourLunch) {
		this.hourLunch = hourLunch;
	}

	public ProfileEnum getProfileEnum() {
		return profileEnum;
	}

	public void setProfileEnum(ProfileEnum profileEnum) {
		this.profileEnum = profileEnum;
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

	public List<PointReleasesEntity> getPointReleases() {
		return pointReleases;
	}

	public void setPointReleases(List<PointReleasesEntity> pointReleases) {
		this.pointReleases = pointReleases;
	}
	
	public CompanyEntity getCompanyEntity() {
		return companyEntity;
	}

	public void setCompanyEntity(CompanyEntity companyEntity) {
		this.companyEntity = companyEntity;
	}

	@Override
	public String toString() {
		return "EmployeesEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", cpf="
				+ cpf + ", hourValue=" + hourValue + ", hourPerDay=" + hourPerDay + ", hourLunch=" + hourLunch
				+ ", profileEnum=" + profileEnum + ", creationDate=" + creationDate + ", updateDate=" + updateDate
				+ ", companyEntity=" + companyEntity + "]";
	}

	
}