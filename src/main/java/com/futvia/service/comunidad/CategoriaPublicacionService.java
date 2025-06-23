package com.futvia.service.comunidad;

import com.futvia.dto.comunidad.CategoriaPublicacionDTO;

import java.util.List;

public interface CategoriaPublicacionService {
    List<CategoriaPublicacionDTO> listarTodas();
    CategoriaPublicacionDTO buscarPorId(Long id);
    CategoriaPublicacionDTO crear(CategoriaPublicacionDTO dto);
    CategoriaPublicacionDTO editar(Long id, CategoriaPublicacionDTO dto);
    void eliminar(Long id);
}
