package com.futvia.service.auth;

import com.futvia.dto.auth.UsuarioDTO;
import com.futvia.model.auth.Usuario;
import com.futvia.repository.auth.UsuarioRepository;
import com.futvia.service.auth.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO editar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar campos manualmente si es necesario
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setActivo(dto.isActivo());

        return modelMapper.map(usuarioRepository.save(usuario), UsuarioDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioDTO> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRolesNombre(nombreRol).stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> buscarPorLiga(Long ligaId) {
        return usuarioRepository.findByLigaAsignadaId(ligaId).stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }
}
