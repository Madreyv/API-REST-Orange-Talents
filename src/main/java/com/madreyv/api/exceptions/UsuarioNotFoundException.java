package com.madreyv.api.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
	public UsuarioNotFoundException(String cpf){
		super("Não foi possível encontrar o usuário com CPF= " + cpf);
	}
}
