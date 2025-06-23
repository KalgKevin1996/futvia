package com.futvia.service.reporte;

import com.futvia.dto.reporte.ReporteGeneralDTO;

import java.util.List;

public interface ReporteService {

    long totalPartidosJugados();

    long totalGolesMarcados();

    long totalTarjetasAmarillas();

    long totalTarjetasRojas();

    long totalJugadoresRegistrados();

    List<ReporteGeneralDTO> top5Goleadores();

    List<ReporteGeneralDTO> top5Asistidores();

    List<ReporteGeneralDTO> top5JugadoresConMasPartidos();

    List<ReporteGeneralDTO> top5CalificacionesPromedio();

    List<ReporteGeneralDTO> porterosMenosVencidos();
}
