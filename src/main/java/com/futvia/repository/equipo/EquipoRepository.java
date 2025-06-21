package com.futvia.repository.equipo;

import com.futvia.model.equipo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByLigaId(Long ligaId);
    List<Equipo> findByCategoriaId(Long categoriaId);
}
