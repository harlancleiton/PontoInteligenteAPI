package br.harlan.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "company")
public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = 6641590400232799000L;

	//@Id
	private String id;
	private String socialName;
	private String cnpj;
	private Date creationDate;
	private Date updateDate;
	//@DBRef
	private List<EmployeesEntity> employees;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSocialName() {
		return socialName;
	}

	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public List<EmployeesEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeesEntity> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "CompanyDocument [id=" + id + ", socialName=" + socialName + ", cnpj=" + cnpj + ", creationDate="
				+ creationDate + ", updateDate=" + updateDate + "]";
	}
}