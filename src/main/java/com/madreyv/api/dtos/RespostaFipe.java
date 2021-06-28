package com.madreyv.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RespostaFipe {
	
	@JsonProperty("Valor")
	private String valor;
	@JsonProperty("Marca")
	private String marca;
	@JsonProperty("Modelo")
	private String modelo;
	@JsonProperty("AnoModelo")
	private Integer anoModelo;
	@JsonProperty("Combustivel")
	private String combustivel;
	@JsonProperty("CodigoFipe")
	private String codigoFipe;
	@JsonProperty("MesReferencia")
	private String mesReferencia;
	@JsonProperty("TipoVeiculo")
	private Integer tipoVeiculo;
	@JsonProperty("SiglaCombustivel")
	private String siglaCombustivel;
	
	
	public RespostaFipe() {
		super();
	}
	

	public String getValor() {
		return this.valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getmodelo() {
		return this.modelo;
	}
	public void setmodelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getanomodelo() {
		return this.anoModelo;
	}
	public void setanomodelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getcombustivel() {
		return combustivel;
	}
	public void setcombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getcodigoFipe() {
		return codigoFipe;
	}
	public void setcodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}
	public String getmesReferencia() {
		return mesReferencia;
	}
	public void setmesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	public Integer gettipoVeiculo() {
		return tipoVeiculo;
	}
	public void settipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public String getsiglacombustivel() {
		return siglaCombustivel;
	}
	public void setsiglacombustivel(String siglaCombustivel) {
		this.siglaCombustivel = siglaCombustivel;
	}


	@Override
	public String toString() {
		return "RespostaFipe [valor=" + valor + ", marca=" + marca + ", modelo=" + modelo + ", anoModelo=" + anoModelo
				+ ", combustivel=" + combustivel + ", codigoFipe=" + codigoFipe + ", mesReferencia=" + mesReferencia
				+ ", tipoVeiculo=" + tipoVeiculo + ", siglaCombustivel=" + siglaCombustivel + "]";
	}
	
	
	
}
