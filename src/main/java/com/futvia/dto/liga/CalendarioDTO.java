package com.futvia.dto.liga;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioDTO {
    private Long id;
    private int jornada;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String observaciones;
    private String temporadaNombre; // ejemplo: "Apertura 2025"
}
