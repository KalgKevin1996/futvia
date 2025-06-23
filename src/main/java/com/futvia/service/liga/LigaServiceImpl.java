package com.futvia.service.liga;

import com.futvia.dto.liga.LigaDTO;
import com.futvia.dto.liga.LigaFormDTO;
import com.futvia.model.liga.Liga;
import com.futvia.repository.liga.LigaRepository;
import com.futvia.service.liga.LigaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LigaServiceImpl implements LigaService {

    private final LigaRepository ligaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LigaDTO> listarTodas() {
        return ligaRepository.findAll().stream()
                .map(liga -> modelMapper.map(liga, LigaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LigaDTO buscarPorId(Long id) {
        Liga liga = ligaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Liga no encontrada con ID: " + id));
        return modelMapper.map(liga, LigaDTO.class);
    }

    @Override
    public LigaDTO crear(LigaFormDTO dto) {
        Liga liga = modelMapper.map(dto, Liga.class);
        return modelMapper.map(ligaRepository.save(liga), LigaDTO.class);
    }

    @Override
    public LigaDTO editar(Long id, LigaFormDTO dto) {
        Liga liga = ligaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Liga no encontrada con ID: " + id));

        liga.setNombre(dto.getNombre());
        liga.setDescripcion(dto.getDescripcion());
        liga.setFechaInicio(dto.getFechaInicio());
        liga.setFechaFin(dto.getFechaFin());
        liga.setUbicacion(dto.getUbicacion());
        liga.setActiva(dto.isActiva());

        return modelMapper.map(ligaRepository.save(liga), LigaDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!ligaRepository.existsById(id)) {
            throw new EntityNotFoundException("Liga no encontrada con ID: " + id);
        }
        ligaRepository.deleteById(id);
    }
}
