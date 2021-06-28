package com.madreyv.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.madreyv.api.exceptions.UsuarioNotFoundException;
import com.madreyv.api.modelos.Usuario;
import com.madreyv.api.modelos.Veiculo;
import com.madreyv.api.repositories.UsuarioRepositorio;
import com.madreyv.api.repositories.VeiculoRepositorio;

import net.minidev.json.JSONObject;

@RestController
public class UsuarioControlador {
	
	
	private final UsuarioRepositorio repositorio;
	private final VeiculoRepositorio veiculoRepositorio;
	
	UsuarioControlador(UsuarioRepositorio repositorio, VeiculoRepositorio veiculoRepositorio){
		this.repositorio = repositorio;
		this.veiculoRepositorio = veiculoRepositorio;
	}

	
	@GetMapping("/usuarios")
	List<Usuario> listarUsuarios(){
		return repositorio.findAll();
	}
	
	@GetMapping("/usuarios/{cpf}")
	Usuario pesquisarUsuario(@PathVariable String cpf) {
		return repositorio.findById(cpf)
				.orElseThrow(() -> new UsuarioNotFoundException(cpf));
	}
	
	@GetMapping("/usuarios/veiculos/{cpf}")
	List <Veiculo> pesquisarVeiculosUsuario(@PathVariable String cpf) {
		
		return veiculoRepositorio.findAll().stream()
				.filter(veiculo -> veiculo.getUsuario().getCpf().equals(cpf))
				.collect(Collectors.toList());
	}
	

	@PostMapping("/usuarios")
	ResponseEntity<?> novoUsuario(@RequestBody Usuario usuario) {
		try {
			repositorio.save(usuario);
			JSONObject jsonObject = new JSONObject();
			jsonObject.appendField("message", "Usuário cadastrado com sucesso!");
			return ResponseEntity
		            .status(HttpStatus.CREATED)
		            .body(jsonObject);
		}catch(Exception e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.appendField("message", "Usuário não cadastrado, verifique se preencheu os campos Corretamente");
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(jsonObject);
		}
			
			
		
	}
	
	@PutMapping("/usuarios/{cpf}")
	Usuario editarUsuario(@RequestBody Usuario novoUsuario, @PathVariable String cpf) {
		return repositorio.findById(cpf)
				.map(usuario -> {
					usuario.setNome(novoUsuario.getNome());
					usuario.setEmail(novoUsuario.getEmail());
					usuario.setNascimento(novoUsuario.getNascimento());
					return repositorio.save(usuario);
				}).orElseGet(() -> {
					novoUsuario.setCpf(cpf);
					return repositorio.save(novoUsuario);
				});
	}
	
	@DeleteMapping("/usuarios/{cpf}")
	void deletarUsuario(@PathVariable String cpf) {
		repositorio.deleteById(cpf);
	}
}
