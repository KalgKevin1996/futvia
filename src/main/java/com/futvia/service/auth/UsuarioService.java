package com.futvia.service.auth;

import com.futvia.dto.auth.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listarTodos();
    UsuarioDTO buscarPorId(Long id);
    UsuarioDTO crear(UsuarioDTO dto);
    UsuarioDTO editar(Long id, UsuarioDTO dto);
    void eliminar(Long id);
    List<UsuarioDTO> buscarPorRol(String nombreRol);
    List<UsuarioDTO> buscarPorLiga(Long ligaId);
}
