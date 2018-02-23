package br.harlan.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = 6641590400232799000L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "social_name", nullable = false)
	private String socialName;

	@Column(name = "cnpj", nullable = false)
	private String cnpj;

	@Column(name = "creation_date", nullable = false)
	private Date creationDate;

	@Column(name = "update_date", nullable = false)
	private Date updateDate;

	@OneToMany(mappedBy = "companyEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EmployeesEntity> employees;

	public CompanyEntity() {
	}

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