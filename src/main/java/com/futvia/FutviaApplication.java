package com.futvia;

import com.futvia.model.Usuario;
import com.futvia.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FutviaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutviaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {
			Usuario u = new Usuario();
			u.setNombre("admin");
			u.setEmail("admin@futvia.com");
			usuarioRepository.save(u);
		};
	}
}
