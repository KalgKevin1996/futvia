package com.futvia.dto.reporte;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteGeneralDTO {
    private Long jugadorId;
    private String nombreCompleto;
    private String equipoNombre;
    private int valor;              // Puede representar goles, asistencias, partidos, tarjetas, etc.
    private double promedio;       // Usado para promedio de calificación o goles recibidos
}
