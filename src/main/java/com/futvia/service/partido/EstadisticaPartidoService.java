package com.futvia.service.partido;

import com.futvia.dto.partido.EstadisticaPartidoDTO;
import com.futvia.dto.partido.EstadisticaPartidoFormDTO;

import java.util.List;

public interface EstadisticaPartidoService {
    List<EstadisticaPartidoDTO> listarPorPartido(Long partidoId);
    EstadisticaPartidoDTO crear(EstadisticaPartidoFormDTO dto);
    EstadisticaPartidoDTO editar(Long id, EstadisticaPartidoFormDTO dto);
    void eliminar(Long id);
}
