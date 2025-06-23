package com.futvia.service.partido;

import com.futvia.dto.partido.PartidoDTO;
import com.futvia.dto.partido.PartidoFormDTO;

import java.util.List;

public interface PartidoService {
    List<PartidoDTO> listarTodos();
    PartidoDTO buscarPorId(Long id);
    PartidoDTO crear(PartidoFormDTO dto);
    PartidoDTO editar(Long id, PartidoFormDTO dto);
    void eliminar(Long id);
}
