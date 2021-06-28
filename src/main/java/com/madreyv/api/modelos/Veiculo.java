/*O segundo passo é criar um cadastro de veículos, sendo obrigatórios: Marca, Modelo do
Veículo e Ano. E o serviço deve consumir a API da FIPE (https://deividfortuna.github.
io/fipe/) para obter os dados do valor do veículo baseado nas informações inseridas.*/


package com.madreyv.api.modelos;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue
	@Column(unique=true)
	private Long id;
	
	@NotBlank
	@Column
	private String modelo;
	
	@NotBlank
	@Column
	private String marca;
	
	@NotBlank
	@Column
	private String ano;
	
	@NotBlank
	@Column
	private String valor;
	
	@NotBlank
	@Column
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="USUARIO_CPF")
	@JsonBackReference
	private Usuario usuario;
	
	public Veiculo() {};

	public Veiculo(@NotBlank String tipo ,@NotBlank String modelo, @NotBlank String marca, @NotBlank String ano, @NotBlank String valor) {
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public String getDiaRodizio() {
		return this.diaRodizioPorExtenso();
	}
	
	public boolean getRodizioAtivo() {
		return this.rodizioAtivo(this.diaRodizio());
	}
	
	private Integer diaRodizio() {
		String ano = this.getAno().split(" ")[0];
		Integer diaRodizio = Integer.parseInt(String.valueOf(ano.charAt(ano.length() - 1))) + 1 ;//pega o ultimo valor da placa como inteiro
		return diaRodizio;
	}
	
	private String diaRodizioPorExtenso() {
		Integer diaRodizio = this.diaRodizio();
		String [] dias = {"segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira"};
	
		if(diaRodizio % 2 == 0) {
			return dias[diaRodizio/2];
		}else {
			return dias[(diaRodizio - 1) /2];
		}
	}
	
	private Integer diaPermitido(Integer ultimoDigitoRodizio) {
		switch(ultimoDigitoRodizio) {
			case 0:
			case 1:
				return 2;
			case 2:
			case 3:
				return 3;
			case 4:
			case 5:
				return 4;
			case 6:
			case 7:
				return 5;
			case 8:
			case 9:
				return 6;
				
		}
		
		return 0;
	}
	
	public Boolean rodizioAtivo(Integer diaRodizio) {		
		return this.diaPermitido(diaRodizio) == new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", modelo=" + modelo + ", marca=" + marca + ", ano=" + ano + ", valor=" + valor
				+ ", tipo=" + tipo + "diaRodizio= "+ this.getDiaRodizio()+ "rodizioAtivo= " + this.getRodizioAtivo() + ", usuario=" + usuario.getNome() + "]";
	}
	
}
