package com.madreyv.api.dtos;

import java.util.List;
import java.util.stream.Collectors;

public class ModelosFipe {
	private List<ServicoFipe> modelos;
	private List<ServicoFipe> anos;

	public List<ServicoFipe> getModelosEAnos() {
		this.modelos.addAll(anos);
		return modelos;
	}
	
	public List<ServicoFipe> getModelos() {
		return this.modelos;
	}
	
	public List<ServicoFipe> getAnos() {
		
		return this.anos;
	}

	public void setModelos(List<ServicoFipe> modelos, List<ServicoFipe> anos ) {
		this.modelos = modelos;
		this.anos = anos;
	}
	
	public List<ServicoFipe> filtrar(String parametro) {

		return this.getModelosEAnos().stream().filter(modelo -> modelo.getCodigoPorMarca(parametro) != null).collect(Collectors.toList());
	}
	
}
