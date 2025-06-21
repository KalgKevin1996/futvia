package com.futvia.repository.liga;

import com.futvia.model.liga.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
    List<Calendario> findByTemporadaId(Long temporadaId);
}
