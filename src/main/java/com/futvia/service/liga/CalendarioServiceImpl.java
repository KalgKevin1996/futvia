package com.futvia.service.liga;

import com.futvia.dto.liga.CalendarioDTO;
import com.futvia.dto.liga.CalendarioFormDTO;
import com.futvia.model.liga.Calendario;
import com.futvia.model.liga.Temporada;
import com.futvia.repository.liga.CalendarioRepository;
import com.futvia.repository.liga.TemporadaRepository;
import com.futvia.service.liga.CalendarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarioServiceImpl implements CalendarioService {

    private final CalendarioRepository calendarioRepository;
    private final TemporadaRepository temporadaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CalendarioDTO> listarPorTemporada(Long temporadaId) {
        return calendarioRepository.findByTemporadaId(temporadaId).stream()
                .map(cal -> {
                    CalendarioDTO dto = modelMapper.map(cal, CalendarioDTO.class);
                    dto.setTemporadaNombre(cal.getTemporada().getNombre());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public CalendarioDTO buscarPorId(Long id) {
        Calendario calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Calendario no encontrado con ID: " + id));
        CalendarioDTO dto = modelMapper.map(calendario, CalendarioDTO.class);
        dto.setTemporadaNombre(calendario.getTemporada().getNombre());
        return dto;
    }

    @Override
    public CalendarioDTO crear(CalendarioFormDTO dto) {
        Temporada temporada = temporadaRepository.findById(dto.getTemporadaId())
                .orElseThrow(() -> new EntityNotFoundException("Temporada no encontrada con ID: " + dto.getTemporadaId()));

        Calendario calendario = modelMapper.map(dto, Calendario.class);
        calendario.setTemporada(temporada);

        return modelMapper.map(calendarioRepository.save(calendario), CalendarioDTO.class);
    }

    @Override
    public CalendarioDTO editar(Long id, CalendarioFormDTO dto) {
        Calendario calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Calendario no encontrado con ID: " + id));

        calendario.setJornada(dto.getJornada());
        calendario.setFechaInicio(dto.getFechaInicio());
        calendario.setFechaFin(dto.getFechaFin());
        calendario.setObservaciones(dto.getObservaciones());

        return modelMapper.map(calendarioRepository.save(calendario), CalendarioDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!calendarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Calendario no encontrado con ID: " + id);
        }
        calendarioRepository.deleteById(id);
    }
}
