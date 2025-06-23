package com.futvia.service.partido;

import com.futvia.dto.partido.PartidoDTO;
import com.futvia.dto.partido.PartidoFormDTO;
import com.futvia.model.equipo.Equipo;
import com.futvia.model.liga.CategoriaCompetencia;
import com.futvia.model.liga.Temporada;
import com.futvia.model.partido.Partido;
import com.futvia.repository.equipo.EquipoRepository;
import com.futvia.repository.liga.CategoriaCompetenciaRepository;
import com.futvia.repository.liga.TemporadaRepository;
import com.futvia.repository.partido.PartidoRepository;
import com.futvia.service.partido.PartidoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;
    private final TemporadaRepository temporadaRepository;
    private final CategoriaCompetenciaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PartidoDTO> listarTodos() {
        return partidoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public PartidoDTO buscarPorId(Long id) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + id));
        return convertirADTO(partido);
    }

    @Override
    public PartidoDTO crear(PartidoFormDTO dto) {
        Partido partido = convertirAEntidad(dto);
        partidoRepository.save(partido);
        return convertirADTO(partido);
    }

    @Override
    public PartidoDTO editar(Long id, PartidoFormDTO dto) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + id));

        partido.setFechaHora(dto.getFechaHora());
        partido.setEstado(dto.getEstado());
        partido.setUbicacion(dto.getUbicacion());
        partido.setGolesLocal(dto.getGolesLocal());
        partido.setGolesVisitante(dto.getGolesVisitante());

        Equipo equipoLocal = equipoRepository.findById(dto.getEquipoLocalId())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));
        Equipo equipoVisitante = equipoRepository.findById(dto.getEquipoVisitanteId())
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));
        Temporada temporada = temporadaRepository.findById(dto.getTemporadaId())
                .orElseThrow(() -> new RuntimeException("Temporada no encontrada"));
        CategoriaCompetencia categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setTemporada(temporada);
        partido.setCategoria(categoria);

        partidoRepository.save(partido);
        return convertirADTO(partido);
    }

    @Override
    public void eliminar(Long id) {
        if (!partidoRepository.existsById(id)) {
            throw new RuntimeException("Partido no encontrado con ID: " + id);
        }
        partidoRepository.deleteById(id);
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private Partido convertirAEntidad(PartidoFormDTO dto) {
        Equipo equipoLocal = equipoRepository.findById(dto.getEquipoLocalId())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado"));
        Equipo equipoVisitante = equipoRepository.findById(dto.getEquipoVisitanteId())
                .orElseThrow(() -> new RuntimeException("Equipo visitante no encontrado"));
        Temporada temporada = temporadaRepository.findById(dto.getTemporadaId())
                .orElseThrow(() -> new RuntimeException("Temporada no encontrada"));
        CategoriaCompetencia categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return Partido.builder()
                .fechaHora(dto.getFechaHora())
                .estado(dto.getEstado())
                .ubicacion(dto.getUbicacion())
                .golesLocal(dto.getGolesLocal())
                .golesVisitante(dto.getGolesVisitante())
                .equipoLocal(equipoLocal)
                .equipoVisitante(equipoVisitante)
                .temporada(temporada)
                .categoria(categoria)
                .build();
    }

    private PartidoDTO convertirADTO(Partido partido) {
        return PartidoDTO.builder()
                .id(partido.getId())
                .fechaHora(partido.getFechaHora())
                .estado(partido.getEstado())
                .ubicacion(partido.getUbicacion())
                .golesLocal(partido.getGolesLocal())
                .golesVisitante(partido.getGolesVisitante())
                .equipoLocalNombre(partido.getEquipoLocal().getNombre())
                .equipoVisitanteNombre(partido.getEquipoVisitante().getNombre())
                .temporadaNombre(partido.getTemporada().getNombre())
                .categoriaNombre(partido.getCategoria().getNombre())
                .build();
    }
}
