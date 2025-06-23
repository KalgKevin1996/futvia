package com.futvia.service.comunidad;

import com.futvia.dto.comunidad.ComentarioDTO;
import com.futvia.dto.comunidad.ComentarioFormDTO;

import java.util.List;

public interface ComentarioService {

    List<ComentarioDTO> listarPorPublicacion(Long publicacionId);

    ComentarioDTO crear(ComentarioFormDTO dto, Long autorId);

    ComentarioDTO editar(Long id, ComentarioFormDTO dto);

    void eliminar(Long id);

    ComentarioDTO buscarPorId(Long id);
}
