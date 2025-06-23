package com.futvia.service.partido;

import com.futvia.dto.partido.EstadisticaPartidoDTO;
import com.futvia.dto.partido.EstadisticaPartidoFormDTO;
import com.futvia.model.equipo.Jugador;
import com.futvia.model.partido.EstadisticaPartido;
import com.futvia.model.partido.Partido;
import com.futvia.repository.equipo.JugadorRepository;
import com.futvia.repository.partido.EstadisticaPartidoRepository;
import com.futvia.repository.partido.PartidoRepository;
import com.futvia.service.partido.EstadisticaPartidoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadisticaPartidoServiceImpl implements EstadisticaPartidoService {

    private final EstadisticaPartidoRepository estadisticaRepository;
    private final PartidoRepository partidoRepository;
    private final JugadorRepository jugadorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EstadisticaPartidoDTO> listarPorPartido(Long partidoId) {
        return estadisticaRepository.findByPartidoId(partidoId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstadisticaPartidoDTO crear(EstadisticaPartidoFormDTO dto) {
        EstadisticaPartido estadistica = convertirAEntidad(dto);
        estadisticaRepository.save(estadistica);
        return convertirADTO(estadistica);
    }

    @Override
    public EstadisticaPartidoDTO editar(Long id, EstadisticaPartidoFormDTO dto) {
        EstadisticaPartido estadistica = estadisticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada con ID: " + id));

        estadistica.setGoles(dto.getGoles());
        estadistica.setAsistencias(dto.getAsistencias());
        estadistica.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        estadistica.setTarjetasRojas(dto.getTarjetasRojas());
        estadistica.setMinutosJugados(dto.getMinutosJugados());
        estadistica.setCalificacion(dto.getCalificacion());

        estadisticaRepository.save(estadistica);
        return convertirADTO(estadistica);
    }

    @Override
    public void eliminar(Long id) {
        if (!estadisticaRepository.existsById(id)) {
            throw new RuntimeException("Estadística no encontrada con ID: " + id);
        }
        estadisticaRepository.deleteById(id);
    }

    // ================= MÉTODOS AUXILIARES =================

    private EstadisticaPartido convertirAEntidad(EstadisticaPartidoFormDTO dto) {
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        Partido partido = partidoRepository.findById(dto.getPartidoId())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        return EstadisticaPartido.builder()
                .goles(dto.getGoles())
                .asistencias(dto.getAsistencias())
                .tarjetasAmarillas(dto.getTarjetasAmarillas())
                .tarjetasRojas(dto.getTarjetasRojas())
                .minutosJugados(dto.getMinutosJugados())
                .calificacion(dto.getCalificacion())
                .jugador(jugador)
                .partido(partido)
                .build();
    }

    private EstadisticaPartidoDTO convertirADTO(EstadisticaPartido estadistica) {
        return EstadisticaPartidoDTO.builder()
                .id(estadistica.getId())
                .goles(estadistica.getGoles())
                .asistencias(estadistica.getAsistencias())
                .tarjetasAmarillas(estadistica.getTarjetasAmarillas())
                .tarjetasRojas(estadistica.getTarjetasRojas())
                .minutosJugados(estadistica.getMinutosJugados())
                .calificacion(estadistica.getCalificacion())
                .nombreJugador(estadistica.getJugador().getNombre() + " " + estadistica.getJugador().getApellido())
                .partidoId(estadistica.getPartido().getId())
                .build();
    }
}
