package com.futvia.repository.auth;

import com.futvia.model.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByActivoTrue();

    List<Usuario> findByLigaAsignadaId(Long ligaId);

    List<Usuario> findByRolesNombre(String nombreRol); // e.g. "JUGADOR", "ENTRENADOR"
}
