package com.futvia.repository.comunidad;

import com.futvia.model.comunidad.CategoriaPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaPublicacionRepository extends JpaRepository<CategoriaPublicacion, Long> {
    Optional<CategoriaPublicacion> findByNombre(String nombre);
}
