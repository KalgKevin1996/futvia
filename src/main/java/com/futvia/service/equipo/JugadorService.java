package com.futvia.service.equipo;

import com.futvia.dto.equipo.JugadorDTO;
import com.futvia.dto.equipo.JugadorFormDTO;

import java.util.List;

public interface JugadorService {
    List<JugadorDTO> listarTodos();
    List<JugadorDTO> listarPorEquipo(Long equipoId);
    JugadorDTO buscarPorId(Long id);
    JugadorDTO crear(JugadorFormDTO dto);
    JugadorDTO editar(Long id, JugadorFormDTO dto);
    void eliminar(Long id);
}
