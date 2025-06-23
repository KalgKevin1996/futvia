package com.futvia.service.reporte;

import com.futvia.dto.reporte.ReporteGeneralDTO;
import com.futvia.model.equipo.Jugador;
import com.futvia.model.partido.EstadisticaPartido;
import com.futvia.repository.equipo.JugadorRepository;
import com.futvia.repository.partido.EstadisticaPartidoRepository;
import com.futvia.repository.partido.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final PartidoRepository partidoRepository;
    private final JugadorRepository jugadorRepository;
    private final EstadisticaPartidoRepository estadisticaPartidoRepository;

    @Override
    public long totalPartidosJugados() {
        return partidoRepository.count();
    }

    @Override
    public long totalGolesMarcados() {
        return estadisticaPartidoRepository.findAll().stream()
                .mapToInt(EstadisticaPartido::getGoles)
                .sum();
    }

    @Override
    public long totalTarjetasAmarillas() {
        return estadisticaPartidoRepository.findAll().stream()
                .mapToInt(EstadisticaPartido::getTarjetasAmarillas)
                .sum();
    }

    @Override
    public long totalTarjetasRojas() {
        return estadisticaPartidoRepository.findAll().stream()
                .mapToInt(EstadisticaPartido::getTarjetasRojas)
                .sum();
    }

    @Override
    public long totalJugadoresRegistrados() {
        return jugadorRepository.count();
    }

    @Override
    public List<ReporteGeneralDTO> top5Goleadores() {
        return jugadorRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Jugador::getGoles).reversed())
                .limit(5)
                .map(j -> ReporteGeneralDTO.builder()
                        .jugadorId(j.getId())
                        .nombreCompleto(j.getNombre() + " " + j.getApellido())
                        .equipoNombre(j.getEquipo() != null ? j.getEquipo().getNombre() : "Sin equipo")
                        .valor(j.getGoles())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteGeneralDTO> top5Asistidores() {
        return jugadorRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Jugador::getAsistencias).reversed())
                .limit(5)
                .map(j -> ReporteGeneralDTO.builder()
                        .jugadorId(j.getId())
                        .nombreCompleto(j.getNombre() + " " + j.getApellido())
                        .equipoNombre(j.getEquipo() != null ? j.getEquipo().getNombre() : "Sin equipo")
                        .valor(j.getAsistencias())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteGeneralDTO> top5JugadoresConMasPartidos() {
        return jugadorRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Jugador::getPartidosJugados).reversed())
                .limit(5)
                .map(j -> ReporteGeneralDTO.builder()
                        .jugadorId(j.getId())
                        .nombreCompleto(j.getNombre() + " " + j.getApellido())
                        .equipoNombre(j.getEquipo() != null ? j.getEquipo().getNombre() : "Sin equipo")
                        .valor(j.getPartidosJugados())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteGeneralDTO> top5CalificacionesPromedio() {
        return jugadorRepository.findAll().stream()
                .filter(j -> j.getPromedioCalificacion() != null)
                .sorted(Comparator.comparingDouble(Jugador::getPromedioCalificacion).reversed())
                .limit(5)
                .map(j -> ReporteGeneralDTO.builder()
                        .jugadorId(j.getId())
                        .nombreCompleto(j.getNombre() + " " + j.getApellido())
                        .equipoNombre(j.getEquipo() != null ? j.getEquipo().getNombre() : "Sin equipo")
                        .promedio(j.getPromedioCalificacion())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteGeneralDTO> porterosMenosVencidos() {
        return jugadorRepository.findAll().stream()
                .filter(j -> j.getPosicion() != null && j.getPosicion().toLowerCase().contains("portero"))
                .filter(j -> j.getPartidosJugados() > 0)
                .sorted(Comparator.comparingDouble(j -> (double) j.getGoles() / j.getPartidosJugados()))
                .limit(5)
                .map(j -> ReporteGeneralDTO.builder()
                        .jugadorId(j.getId())
                        .nombreCompleto(j.getNombre() + " " + j.getApellido())
                        .equipoNombre(j.getEquipo() != null ? j.getEquipo().getNombre() : "Sin equipo")
                        .valor(j.getGoles())
                        .promedio((double) j.getGoles() / j.getPartidosJugados())
                        .build())
                .collect(Collectors.toList());
    }
}
