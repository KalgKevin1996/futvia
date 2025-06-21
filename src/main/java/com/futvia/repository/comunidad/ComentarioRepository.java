package com.futvia.repository.comunidad;

import com.futvia.model.comunidad.Comentario;
import com.futvia.model.comunidad.EstadoModeracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByPublicacionId(Long publicacionId);

    List<Comentario> findByAutorId(Long usuarioId);

    List<Comentario> findByEstadoModeracion(EstadoModeracion estado);
}
