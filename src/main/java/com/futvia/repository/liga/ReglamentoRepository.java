package com.futvia.repository.liga;

import com.futvia.model.liga.Reglamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReglamentoRepository extends JpaRepository<Reglamento, Long> {
    List<Reglamento> findByLigaId(Long ligaId);
    List<Reglamento> findByPublicoTrue();
}
