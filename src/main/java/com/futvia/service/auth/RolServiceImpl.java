package com.futvia.service.auth;

import com.futvia.dto.auth.RolDTO;
import com.futvia.model.auth.Rol;
import com.futvia.repository.auth.RolRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RolDTO> listarTodos() {
        return rolRepository.findAll().stream()
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RolDTO buscarPorNombre(String nombre) {
        Rol rol = rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombre));
        return modelMapper.map(rol, RolDTO.class);
    }
}
