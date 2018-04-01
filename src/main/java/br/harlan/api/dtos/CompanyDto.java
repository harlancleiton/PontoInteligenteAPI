package br.harlan.api.dtos;

public class CompanyDto {
	private Long id;
	private String socialName;
	private String cnpj;
	
	public CompanyDto() {
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

	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", socialName=" + socialName + ", cnpj=" + cnpj + "]";
	}
}