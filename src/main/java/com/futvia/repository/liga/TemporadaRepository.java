package com.futvia.repository.liga;

import com.futvia.model.liga.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporadaRepository extends JpaRepository<Temporada, Long> {
    List<Temporada> findByLigaId(Long ligaId);
    List<Temporada> findByActivaTrue();
}
