package com.madreyv.api;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.madreyv.api.modelos.Usuario;
import com.madreyv.api.repositories.UsuarioRepositorio;

@Configuration
public class CarregarDB {
	
	private static final Logger log = LoggerFactory.getLogger(CarregarDB.class);

	  @Bean
	  CommandLineRunner initDatabase(UsuarioRepositorio repositorio) {
		
	    return args -> {
	      log.info("Preloading " + repositorio.save(new Usuario("Madreyv", "435.382.470-80", "madreyv@gmail.com", new Date())));
	      log.info("Preloading " + repositorio.save(new Usuario("Frodo Baggins", "096.433.870-03","frodo@gmail.com", new Date())));
	    };
	  }
	
}
