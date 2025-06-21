package com.futvia.repository.partido;

import com.futvia.model.partido.IncidenciaPartido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaPartidoRepository extends JpaRepository<IncidenciaPartido, Long> {
    List<IncidenciaPartido> findByPartidoId(Long partidoId);
}
