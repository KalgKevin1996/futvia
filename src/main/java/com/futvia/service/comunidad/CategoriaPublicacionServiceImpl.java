package com.futvia.service.comunidad;

import com.futvia.dto.comunidad.CategoriaPublicacionDTO;
import com.futvia.model.comunidad.CategoriaPublicacion;
import com.futvia.repository.comunidad.CategoriaPublicacionRepository;
import com.futvia.service.comunidad.CategoriaPublicacionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaPublicacionServiceImpl implements CategoriaPublicacionService {

    private final CategoriaPublicacionRepository categoriaRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoriaPublicacionDTO> listarTodas() {
        return categoriaRepo.findAll().stream()
                .map(cat -> modelMapper.map(cat, CategoriaPublicacionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaPublicacionDTO buscarPorId(Long id) {
        CategoriaPublicacion categoria = categoriaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + id));
        return modelMapper.map(categoria, CategoriaPublicacionDTO.class);
    }

    @Override
    public CategoriaPublicacionDTO crear(CategoriaPublicacionDTO dto) {
        CategoriaPublicacion categoria = modelMapper.map(dto, CategoriaPublicacion.class);
        return modelMapper.map(categoriaRepo.save(categoria), CategoriaPublicacionDTO.class);
    }

    @Override
    public CategoriaPublicacionDTO editar(Long id, CategoriaPublicacionDTO dto) {
        CategoriaPublicacion existente = categoriaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + id));
        existente.setNombre(dto.getNombre());
        return modelMapper.map(categoriaRepo.save(existente), CategoriaPublicacionDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaRepo.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada con ID: " + id);
        }
        categoriaRepo.deleteById(id);
    }
}
