package com.futvia.service.liga;

import com.futvia.dto.liga.TemporadaDTO;
import com.futvia.dto.liga.TemporadaFormDTO;

import java.util.List;

public interface TemporadaService {
    List<TemporadaDTO> listarTodas();
    List<TemporadaDTO> listarPorLiga(Long ligaId);
    TemporadaDTO buscarPorId(Long id);
    TemporadaDTO crear(TemporadaFormDTO dto);
    TemporadaDTO editar(Long id, TemporadaFormDTO dto);
    void eliminar(Long id);
}
