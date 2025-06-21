package com.futvia.repository.partido;

import com.futvia.model.partido.EstadisticaPartido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadisticaPartidoRepository extends JpaRepository<EstadisticaPartido, Long> {
    List<EstadisticaPartido> findByPartidoId(Long partidoId);
    List<EstadisticaPartido> findByJugadorId(Long jugadorId);
}
