package com.futvia.service.liga;

import com.futvia.dto.liga.TemporadaDTO;
import com.futvia.dto.liga.TemporadaFormDTO;
import com.futvia.model.liga.Liga;
import com.futvia.model.liga.Temporada;
import com.futvia.repository.liga.LigaRepository;
import com.futvia.repository.liga.TemporadaRepository;
import com.futvia.service.liga.TemporadaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemporadaServiceImpl implements TemporadaService {

    private final TemporadaRepository temporadaRepository;
    private final LigaRepository ligaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TemporadaDTO> listarTodas() {
        return temporadaRepository.findAll().stream()
                .map(t -> {
                    TemporadaDTO dto = modelMapper.map(t, TemporadaDTO.class);
                    dto.setLigaNombre(t.getLiga().getNombre());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<TemporadaDTO> listarPorLiga(Long ligaId) {
        return temporadaRepository.findByLigaId(ligaId).stream()
                .map(t -> {
                    TemporadaDTO dto = modelMapper.map(t, TemporadaDTO.class);
                    dto.setLigaNombre(t.getLiga().getNombre());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public TemporadaDTO buscarPorId(Long id) {
        Temporada temporada = temporadaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Temporada no encontrada con ID: " + id));
        TemporadaDTO dto = modelMapper.map(temporada, TemporadaDTO.class);
        dto.setLigaNombre(temporada.getLiga().getNombre());
        return dto;
    }

    @Override
    public TemporadaDTO crear(TemporadaFormDTO dto) {
        Liga liga = ligaRepository.findById(dto.getLigaId())
                .orElseThrow(() -> new EntityNotFoundException("Liga no encontrada con ID: " + dto.getLigaId()));
        Temporada temporada = modelMapper.map(dto, Temporada.class);
        temporada.setLiga(liga);
        return modelMapper.map(temporadaRepository.save(temporada), TemporadaDTO.class);
    }

    @Override
    public TemporadaDTO editar(Long id, TemporadaFormDTO dto) {
        Temporada temporada = temporadaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Temporada no encontrada con ID: " + id));

        temporada.setNombre(dto.getNombre());
        temporada.setFechaInicio(dto.getFechaInicio());
        temporada.setFechaFin(dto.getFechaFin());
        temporada.setFormato(dto.getFormato());
        temporada.setActiva(dto.isActiva());

        return modelMapper.map(temporadaRepository.save(temporada), TemporadaDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!temporadaRepository.existsById(id)) {
            throw new EntityNotFoundException("Temporada no encontrada con ID: " + id);
        }
        temporadaRepository.deleteById(id);
    }
}
