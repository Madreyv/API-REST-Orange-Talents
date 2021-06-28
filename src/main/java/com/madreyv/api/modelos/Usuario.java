package com.madreyv.api.modelos;



/*O primeiro passo deve ser a construção de um cadastro de usuários, sendo obrigatórios:
nome, e-mail, CPF e data de nascimento, sendo que e-mail e CPF devem ser únicos.*/

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CPF;

@Entity(name = "USUARIO")
@Table
public class Usuario {
	
	
	
	@NotBlank
	@Column
	private String nome;
	
	@Id
	@NotBlank
	@Column(unique=true)
	@CPF
	private String cpf;
	
	@NotBlank
	@Email
	@Column(unique=true)
	private String email;
	
	
	@Type(type = "date")
	@Column
	private Date nascimento;
	
	@OneToMany(mappedBy="usuario")
	private List<Veiculo> Veiculos;
	
	@Deprecated
	public Usuario() {};
	
	public Usuario(@NotBlank String nome, @NotBlank String cpf, @NotBlank String email, @NotBlank Date data) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.nascimento = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	
	public List<Veiculo> getVeiculos() {
		return Veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		Veiculos = veiculos;
	}
	
	public void adicionarVeiculos(Veiculo veiculo) {
		this.Veiculos.add(veiculo);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", nascimento=" + nascimento + "]";
	}
	
	
}
