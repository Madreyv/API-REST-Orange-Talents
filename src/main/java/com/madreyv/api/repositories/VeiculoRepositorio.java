package com.madreyv.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madreyv.api.modelos.Veiculo;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long>{

}
