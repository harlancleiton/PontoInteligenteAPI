package br.harlan.api.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CompanyRegistrationDto {

	private Long id;
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 5, max = 255, message = "O nome deve conter no mínimo 5 caracteres, e no máximo 255.")
	private String name;
	@Email(message = "Email invalido")
	@NotEmpty(message = "Email não pode ser vazio.")
	private String email;
	@NotEmpty(message = "Senha não pode ser vazia.")
	private String password;
	@NotEmpty(message = "CPF não pode ser vazio.")
	@CPF(message = "CPF inválido")
	private String cpf;
	@Length(min = 5, max = 200, message = "Razão Social deve conter entre 5 e 200 caracteres.")
	@NotEmpty(message = "Razão Social não pode ser vazia.")
	private String socialName;
	@NotEmpty(message = "CNPJ não pode ser vazio.")
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;

	public CompanyRegistrationDto() {
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
		return "CompanyRegistrationDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", cpf=" + cpf + ", socialName=" + socialName + ", cnpj=" + cnpj + "]";
	}
}