package br.harlan.api.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import br.harlan.api.enums.ProfileEnum;

//@Document(collection = "employees")
public class EmployeesEntity {

	//@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private String cpf;
	private BigDecimal hourValue;
	private Float hourPerDay;
	private Float hourLunch;
	private ProfileEnum profileEnum;
	private Date creationDate;
	private Date updateDate;
	//@DBRef
	private List<PointReleasesEntity> pointReleases;
	//@DBRef
	private CompanyEntity companyDocument;

	public EmployeesEntity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public void setHourValue(BigDecimal hourValue) {
		this.hourValue = hourValue;
	}

	public Float getHourPerDay() {
		return hourPerDay;
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

	public CompanyEntity getCompanyDocument() {
		return companyDocument;
	}

	public void setCompanyDocument(CompanyEntity companyDocument) {
		this.companyDocument = companyDocument;
	}

	@Override
	public String toString() {
		return "EmployeesDocument [id=" + id + ", name=" + name + ", password=" + password + ", cpf=" + cpf
				+ ", hourValue=" + hourValue + ", hourPerDay=" + hourPerDay + ", hourLunch=" + hourLunch
				+ ", profileEnum=" + profileEnum + ", creationDate=" + creationDate + ", updateDate=" + updateDate
				+ ", companyDocument=" + companyDocument + "]";
	}
}