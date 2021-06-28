package com.madreyv.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madreyv.api.modelos.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>  {
	
}
