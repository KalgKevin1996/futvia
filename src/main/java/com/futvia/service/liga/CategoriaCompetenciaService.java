package com.futvia.service.liga;

import com.futvia.dto.liga.CategoriaCompetenciaDTO;
import com.futvia.dto.liga.CategoriaCompetenciaFormDTO;

import java.util.List;

public interface CategoriaCompetenciaService {
    List<CategoriaCompetenciaDTO> listarTodas();
    List<CategoriaCompetenciaDTO> listarPorLiga(Long ligaId);
    CategoriaCompetenciaDTO buscarPorId(Long id);
    CategoriaCompetenciaDTO crear(CategoriaCompetenciaFormDTO dto);
    CategoriaCompetenciaDTO editar(Long id, CategoriaCompetenciaFormDTO dto);
    void eliminar(Long id);
}
