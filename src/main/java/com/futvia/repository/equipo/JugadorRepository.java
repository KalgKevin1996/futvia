package com.futvia.repository.equipo;

import com.futvia.model.equipo.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByEquipoId(Long equipoId);
    List<Jugador> findByDestacadoDelMesTrue();
    List<Jugador> findByActivoPublicoTrue();
}
