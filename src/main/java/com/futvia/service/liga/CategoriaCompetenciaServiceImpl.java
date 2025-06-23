package com.futvia.service.liga;

import com.futvia.dto.liga.CategoriaCompetenciaDTO;
import com.futvia.dto.liga.CategoriaCompetenciaFormDTO;
import com.futvia.model.liga.CategoriaCompetencia;
import com.futvia.model.liga.Liga;
import com.futvia.repository.liga.CategoriaCompetenciaRepository;
import com.futvia.repository.liga.LigaRepository;
import com.futvia.service.liga.CategoriaCompetenciaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaCompetenciaServiceImpl implements CategoriaCompetenciaService {

    private final CategoriaCompetenciaRepository categoriaRepository;
    private final LigaRepository ligaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoriaCompetenciaDTO> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(c -> {
                    CategoriaCompetenciaDTO dto = modelMapper.map(c, CategoriaCompetenciaDTO.class);
                    dto.setLigaNombre(c.getLiga().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoriaCompetenciaDTO> listarPorLiga(Long ligaId) {
        return categoriaRepository.findByLigaId(ligaId).stream()
                .map(c -> {
                    CategoriaCompetenciaDTO dto = modelMapper.map(c, CategoriaCompetenciaDTO.class);
                    dto.setLigaNombre(c.getLiga().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaCompetenciaDTO buscarPorId(Long id) {
        CategoriaCompetencia categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + id));
        CategoriaCompetenciaDTO dto = modelMapper.map(categoria, CategoriaCompetenciaDTO.class);
        dto.setLigaNombre(categoria.getLiga().getNombre());
        return dto;
    }

    @Override
    public CategoriaCompetenciaDTO crear(CategoriaCompetenciaFormDTO dto) {
        Liga liga = ligaRepository.findById(dto.getLigaId())
                .orElseThrow(() -> new EntityNotFoundException("Liga no encontrada con ID: " + dto.getLigaId()));

        CategoriaCompetencia categoria = modelMapper.map(dto, CategoriaCompetencia.class);
        categoria.setLiga(liga);

        return modelMapper.map(categoriaRepository.save(categoria), CategoriaCompetenciaDTO.class);
    }

    @Override
    public CategoriaCompetenciaDTO editar(Long id, CategoriaCompetenciaFormDTO dto) {
        CategoriaCompetencia categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + id));

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setDiaJuego(dto.getDiaJuego());

        return modelMapper.map(categoriaRepository.save(categoria), CategoriaCompetenciaDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada con ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
