package com.futvia.service.partido;

import com.futvia.dto.partido.IncidenciaPartidoDTO;
import com.futvia.dto.partido.IncidenciaPartidoFormDTO;
import com.futvia.model.partido.IncidenciaPartido;
import com.futvia.model.partido.Partido;
import com.futvia.repository.partido.IncidenciaPartidoRepository;
import com.futvia.repository.partido.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncidenciaPartidoServiceImpl implements IncidenciaPartidoService {

    private final IncidenciaPartidoRepository incidenciaRepo;
    private final PartidoRepository partidoRepo;
    private final ModelMapper mapper;

    @Override
    public List<IncidenciaPartidoDTO> listarPorPartido(Long partidoId) {
        return incidenciaRepo.findByPartidoId(partidoId).stream()
                .map(i -> mapper.map(i, IncidenciaPartidoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public IncidenciaPartidoDTO crear(IncidenciaPartidoFormDTO dto) {
        Partido partido = partidoRepo.findById(dto.getPartidoId())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        IncidenciaPartido incidencia = mapper.map(dto, IncidenciaPartido.class);
        incidencia.setPartido(partido);

        return mapper.map(incidenciaRepo.save(incidencia), IncidenciaPartidoDTO.class);
    }

    @Override
    public IncidenciaPartidoDTO editar(Long id, IncidenciaPartidoFormDTO dto) {
        IncidenciaPartido incidencia = incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        Partido partido = partidoRepo.findById(dto.getPartidoId())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        incidencia.setTipo(dto.getTipo());
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setMinuto(dto.getMinuto());
        incidencia.setPartido(partido);

        return mapper.map(incidenciaRepo.save(incidencia), IncidenciaPartidoDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        incidenciaRepo.deleteById(id);
    }

    @Override
    public IncidenciaPartidoDTO buscarPorId(Long id) {
        return incidenciaRepo.findById(id)
                .map(i -> mapper.map(i, IncidenciaPartidoDTO.class))
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
    }
}
