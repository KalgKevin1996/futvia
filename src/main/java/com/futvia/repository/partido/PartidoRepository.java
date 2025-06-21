package com.futvia.repository.partido;

import com.futvia.model.partido.Partido;
import com.futvia.model.partido.EstadoPartido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    List<Partido> findByTemporadaId(Long temporadaId);
    List<Partido> findByCategoriaId(Long categoriaId);
    List<Partido> findByEstado(EstadoPartido estado);
}
