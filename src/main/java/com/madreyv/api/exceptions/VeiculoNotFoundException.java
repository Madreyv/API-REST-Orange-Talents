package com.madreyv.api.exceptions;

public class VeiculoNotFoundException extends RuntimeException {
	public VeiculoNotFoundException(String mensagem) {
		super(mensagem);
	}
}
