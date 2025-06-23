package com.futvia.service.comunidad;

import com.futvia.dto.comunidad.PublicacionDTO;
import com.futvia.dto.comunidad.PublicacionFormDTO;

import java.util.List;

public interface PublicacionService {

    List<PublicacionDTO> listarTodas();

    List<PublicacionDTO> listarVisibles();

    List<PublicacionDTO> listarPorAutor(Long autorId);

    List<PublicacionDTO> listarPorCategoria(Long categoriaId);

    PublicacionDTO crear(PublicacionFormDTO dto);

    PublicacionDTO editar(Long id, PublicacionFormDTO dto);

    void eliminar(Long id);

    PublicacionDTO buscarPorId(Long id);
}
