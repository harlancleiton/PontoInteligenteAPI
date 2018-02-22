package br.harlan.api.documents;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import br.harlan.api.enums.ProfileEnum;

@Document
public class EmployeesDocument {

	@Id
	private String id;
	private String name;
	private String password;
	private String cpf;
	private BigDecimal hourValue;
	private Float hourPerDay;
	private Float hourLunch;
	private ProfileEnum profileEnum;
	private Date creationDate;
	private Date updateDate;
	@DBRef
	private List<PointReleases> pointReleases;
	
	public EmployeesDocument() {
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

	public List<PointReleases> getPointReleases() {
		return pointReleases;
	}

	public void setPointReleases(List<PointReleases> pointReleases) {
		this.pointReleases = pointReleases;
	}

	@Override
	public String toString() {
		return "EmployeesDocuments [id=" + id + ", name=" + name + "]";
	}
}