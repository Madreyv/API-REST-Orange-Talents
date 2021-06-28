package com.madreyv.api.servicos;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.madreyv.api.dtos.ModelosFipe;
import com.madreyv.api.dtos.RespostaFipe;
import com.madreyv.api.dtos.ServicoFipe;

@FeignClient(url="https://parallelum.com.br/fipe/api/v1/", name="FipeTabela")
public interface FipeServico {
	
	@GetMapping("{tipo}/marcas")
	public List<ServicoFipe> buscaMarca(@PathVariable String tipo);
	
	@GetMapping("{tipo}/marcas/{cod}/modelos")
	public ModelosFipe buscaModelo(@PathVariable String tipo, @PathVariable String cod); //ok
	
	@GetMapping("{tipoVeiculo}/marcas/{codMarca}/modelos/{codModelo}/anos")//ok
	public List<ServicoFipe> buscaAno(@PathVariable String tipoVeiculo, @PathVariable String codMarca, @PathVariable String codModelo);

	@GetMapping("{tipoVeiculo}/marcas/{codMarca}/modelos/{codModelo}/anos/{codAno}")//notok
	public RespostaFipe buscaValor(@PathVariable String tipoVeiculo, @PathVariable String codMarca, @PathVariable String codModelo, @PathVariable String codAno);
}
