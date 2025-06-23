package com.futvia.service.equipo;

import com.futvia.dto.equipo.EquipoDTO;
import com.futvia.dto.equipo.EquipoFormDTO;

import java.util.List;

public interface EquipoService {
    List<EquipoDTO> listarTodos();
    EquipoDTO buscarPorId(Long id);
    EquipoDTO crear(EquipoFormDTO dto);
    EquipoDTO editar(Long id, EquipoFormDTO dto);
    void eliminar(Long id);
}
