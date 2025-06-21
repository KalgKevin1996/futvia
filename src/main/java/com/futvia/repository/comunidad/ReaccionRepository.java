package com.futvia.repository.comunidad;

import com.futvia.model.comunidad.Reaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    List<Reaccion> findByPublicacionId(Long publicacionId);
    List<Reaccion> findByComentarioId(Long comentarioId);
}
