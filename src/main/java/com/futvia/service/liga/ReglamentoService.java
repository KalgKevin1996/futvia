package com.futvia.service.liga;

import com.futvia.dto.liga.ReglamentoDTO;
import com.futvia.dto.liga.ReglamentoFormDTO;

import java.util.List;

public interface ReglamentoService {
    List<ReglamentoDTO> listarTodos();
    List<ReglamentoDTO> listarPorLiga(Long ligaId);
    ReglamentoDTO buscarPorId(Long id);
    ReglamentoDTO crear(ReglamentoFormDTO dto);
    ReglamentoDTO editar(Long id, ReglamentoFormDTO dto);
    void eliminar(Long id);
}
