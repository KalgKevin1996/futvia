package com.futvia.service.partido;

import com.futvia.dto.partido.IncidenciaPartidoDTO;
import com.futvia.dto.partido.IncidenciaPartidoFormDTO;

import java.util.List;

public interface IncidenciaPartidoService {
    List<IncidenciaPartidoDTO> listarPorPartido(Long partidoId);
    IncidenciaPartidoDTO crear(IncidenciaPartidoFormDTO dto);
    IncidenciaPartidoDTO editar(Long id, IncidenciaPartidoFormDTO dto);
    void eliminar(Long id);
    IncidenciaPartidoDTO buscarPorId(Long id);
}
