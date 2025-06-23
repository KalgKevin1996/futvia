package com.futvia.service.liga;

import com.futvia.dto.liga.CalendarioDTO;
import com.futvia.dto.liga.CalendarioFormDTO;

import java.util.List;

public interface CalendarioService {
    List<CalendarioDTO> listarPorTemporada(Long temporadaId);
    CalendarioDTO buscarPorId(Long id);
    CalendarioDTO crear(CalendarioFormDTO dto);
    CalendarioDTO editar(Long id, CalendarioFormDTO dto);
    void eliminar(Long id);
}
