package com.madreyv.api.controllers;

import java.util.ArrayList;
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

import com.madreyv.api.dtos.ServicoFipe;
import com.madreyv.api.exceptions.VeiculoNotFoundException;
import com.madreyv.api.modelos.Veiculo;
import com.madreyv.api.repositories.UsuarioRepositorio;
import com.madreyv.api.repositories.VeiculoRepositorio;
import com.madreyv.api.servicos.FipeServico;

import net.minidev.json.JSONObject;

@RestController
public class VeiculoControlador {
	
	private final VeiculoRepositorio repositorio;
	private final FipeServico fipe;
	private final UsuarioRepositorio usuarioRepositorio;
	
	VeiculoControlador (VeiculoRepositorio repositorio, FipeServico fipe, UsuarioRepositorio usuarioRepositorio){
		this.repositorio = repositorio;
		this.fipe = fipe;
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	@GetMapping("/veiculos")
	List<Veiculo> listarVeiculos(){
		return repositorio.findAll();
	}
	
	
	@GetMapping("/veiculo/{id}")
	Veiculo pesquisarVeiculo(@PathVariable long id) {
		return repositorio.findById(id)
				.orElseThrow(() -> new VeiculoNotFoundException("Usuario de CPF: " + id + " não encontrado!"));
	}
	
	private String buscarMarca(Veiculo veiculo) {
		List<String> listaMarcas = new ArrayList<String>();
		var codMarca = fipe.buscaMarca(veiculo.getTipo())
				.stream().filter(marcas -> marcas.getCodigoPorMarca(veiculo.getMarca()) != null)
				.collect(Collectors.toList());
		if(!codMarca.isEmpty()) {
			return codMarca.get(0).getCodigoPorMarca(veiculo.getMarca());
		}else {
			var marcas = fipe.buscaMarca(veiculo.getTipo()).stream()
					.filter(modelo-> modelo.getNome() != null).collect(Collectors.toList());
			for(ServicoFipe servico : marcas) {
				listaMarcas.add(servico.getNome()); 
			}
			throw new VeiculoNotFoundException("Marca Não encontrada! Tente as marcas a seguir" + listaMarcas); 
		}
	}
	
	private String buscarModelo(Veiculo veiculo, String codMarca) {
		List<String> listaModelos = new ArrayList<String>();
		var codModelo = fipe.buscaModelo(veiculo.getTipo(),codMarca)
				.filtrar(veiculo.getModelo());
		if(!codModelo.isEmpty()) {
			return codModelo.get(0).getCodigoPorMarca(veiculo.getModelo());
		}else {
			var modelos = fipe.buscaModelo(veiculo.getTipo(),codMarca).getModelosEAnos();
			for(ServicoFipe servico : modelos) {
				listaModelos.add(servico.getNome()); 
			}
			throw new VeiculoNotFoundException("Modelo Não encontrado!, tente os modelos a seguir" + listaModelos); 
		}
	}
	
	private String buscarAno(Veiculo veiculo, String codMarca, String codModelo) {
		List<String> listaAnos = new ArrayList<String>();
		var codAno = fipe.buscaAno(veiculo.getTipo(),codMarca, codModelo)
				.stream().filter(anos -> anos.getCodigoPorMarca(veiculo.getAno()) != null)
				.collect(Collectors.toList());
		
		if(!codAno.isEmpty()) {
			System.out.print(codAno.get(0).getCodigoPorMarca(veiculo.getModelo()));
			return codAno.get(0).getCodigoPorMarca(veiculo.getAno());
		}else {
			var anos = fipe.buscaAno(veiculo.getTipo(),codMarca, codModelo);
			for(ServicoFipe servico : anos) {
				listaAnos.add(servico.getNome()); 
			}
			
			throw new VeiculoNotFoundException("Modelo Não encontrado!, tente os Anos asseguir" + listaAnos); 
		}
	}
	
	
	@PostMapping("/veiculos/{idUsuario}")
	 ResponseEntity<?>cadastrarVeiculos(@RequestBody Veiculo novoVeiculo, @PathVariable String idUsuario) {
		
		try {
			var codMarca = this.buscarMarca(novoVeiculo);
			var codModelo = this.buscarModelo(novoVeiculo, codMarca);
			var codAno = this.buscarAno(novoVeiculo, codMarca , codModelo);
			
			
			novoVeiculo.setValor(fipe.buscaValor("carros", codMarca, codModelo, codAno).getValor());
			novoVeiculo.setUsuario(usuarioRepositorio.getById(idUsuario));
		
			return new ResponseEntity<Veiculo>(repositorio.save(novoVeiculo), HttpStatus.CREATED);
		}catch(Exception e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.appendField("message", e.getMessage());
	        return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(jsonObject);
		}
	}
	
	@PutMapping("/veiculos/{id}")
	Veiculo editarVeiculos(@RequestBody Veiculo novoVeiculo, @PathVariable Long id) {
		return repositorio.findById(id)
				.map(veiculo -> {
					veiculo.setMarca(novoVeiculo.getMarca());
					veiculo.setAno(novoVeiculo.getAno());
					veiculo.setModelo(novoVeiculo.getModelo());
					veiculo.setTipo(novoVeiculo.getTipo());
					veiculo.setValor(novoVeiculo.getValor());
					veiculo.setUsuario(usuarioRepositorio.getById(novoVeiculo.getUsuario().getCpf()));
					return repositorio.save(veiculo);
				}).orElseGet(() -> {
					novoVeiculo.setId(id);
					return repositorio.save(novoVeiculo);
				});
 
	}
	
	@DeleteMapping("/veiculos/{id}")
	void deletarVeiculo(@PathVariable long id) {
		repositorio.deleteById(id);
	}
}
