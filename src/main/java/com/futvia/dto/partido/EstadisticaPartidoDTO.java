package com.futvia.dto.partido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticaPartidoDTO {
    private Long id;
    private int goles;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int minutosJugados;
    private double calificacion;
    private String jugadorNombreCompleto;
    private Long partidoId;
}
