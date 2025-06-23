package com.futvia.service.auth;

import com.futvia.dto.auth.RolDTO;

import java.util.List;

public interface RolService {
    List<RolDTO> listarTodos();
    RolDTO buscarPorNombre(String nombre);
}
