package com.futvia.dto.partido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticaPartidoFormDTO {
    private int goles;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int minutosJugados;
    private double calificacion;
    private Long jugadorId;
    private Long partidoId;
}
