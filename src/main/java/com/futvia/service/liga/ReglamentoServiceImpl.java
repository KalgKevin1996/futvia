package com.futvia.service.liga;

import com.futvia.dto.liga.ReglamentoDTO;
import com.futvia.dto.liga.ReglamentoFormDTO;
import com.futvia.model.liga.Liga;
import com.futvia.model.liga.Reglamento;
import com.futvia.repository.liga.LigaRepository;
import com.futvia.repository.liga.ReglamentoRepository;
import com.futvia.service.liga.ReglamentoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReglamentoServiceImpl implements ReglamentoService {

    private final ReglamentoRepository reglamentoRepository;
    private final LigaRepository ligaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ReglamentoDTO> listarTodos() {
        return reglamentoRepository.findAll().stream()
                .map(r -> {
                    ReglamentoDTO dto = modelMapper.map(r, ReglamentoDTO.class);
                    dto.setLigaNombre(r.getLiga().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReglamentoDTO> listarPorLiga(Long ligaId) {
        return reglamentoRepository.findByLigaId(ligaId).stream()
                .map(r -> {
                    ReglamentoDTO dto = modelMapper.map(r, ReglamentoDTO.class);
                    dto.setLigaNombre(r.getLiga().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ReglamentoDTO buscarPorId(Long id) {
        Reglamento r = reglamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reglamento no encontrado con ID: " + id));
        ReglamentoDTO dto = modelMapper.map(r, ReglamentoDTO.class);
        dto.setLigaNombre(r.getLiga().getNombre());
        return dto;
    }

    @Override
    public ReglamentoDTO crear(ReglamentoFormDTO dto) {
        Liga liga = ligaRepository.findById(dto.getLigaId())
                .orElseThrow(() -> new EntityNotFoundException("Liga no encontrada con ID: " + dto.getLigaId()));

        Reglamento r = modelMapper.map(dto, Reglamento.class);
        r.setLiga(liga);
        return modelMapper.map(reglamentoRepository.save(r), ReglamentoDTO.class);
    }

    @Override
    public ReglamentoDTO editar(Long id, ReglamentoFormDTO dto) {
        Reglamento r = reglamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reglamento no encontrado con ID: " + id));

        r.setContenido(dto.getContenido());
        r.setVersion(dto.getVersion());
        r.setPublico(dto.isPublico());

        return modelMapper.map(reglamentoRepository.save(r), ReglamentoDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!reglamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Reglamento no encontrado con ID: " + id);
        }
        reglamentoRepository.deleteById(id);
    }
}
