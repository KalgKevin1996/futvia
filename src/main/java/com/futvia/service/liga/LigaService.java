package com.futvia.service.liga;

import com.futvia.dto.liga.LigaDTO;
import com.futvia.dto.liga.LigaFormDTO;

import java.util.List;

public interface LigaService {
    List<LigaDTO> listarTodas();
    LigaDTO buscarPorId(Long id);
    LigaDTO crear(LigaFormDTO dto);
    LigaDTO editar(Long id, LigaFormDTO dto);
    void eliminar(Long id);
}
