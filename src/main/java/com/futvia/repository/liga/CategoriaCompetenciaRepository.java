package com.futvia.repository.liga;

import com.futvia.model.liga.CategoriaCompetencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaCompetenciaRepository extends JpaRepository<CategoriaCompetencia, Long> {
    List<CategoriaCompetencia> findByLigaId(Long ligaId);
}
