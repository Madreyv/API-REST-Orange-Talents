package com.madreyv.api.dtos;


public class ServicoFipe{

	private String nome;
	private String codigo;
	
	public ServicoFipe(String nome, String codigo) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return this.codigo;
	}
	
	public String getCodigoPorMarca(String marca) {
		if(this.getNome().contentEquals(marca)) {
			return this.codigo;
		}else {
			return null;
		} 
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	

	
	
	
}
