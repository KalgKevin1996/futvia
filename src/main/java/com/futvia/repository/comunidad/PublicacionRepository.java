package com.futvia.repository.comunidad;

import com.futvia.model.comunidad.Publicacion;
import com.futvia.model.comunidad.EstadoModeracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByAutorId(Long autorId);
    List<Publicacion> findByCategoriaId(Long categoriaId);
    List<Publicacion> findByEstadoModeracion(EstadoModeracion estado);
    List<Publicacion> findByVisibleTrue();
}
