package com.futvia.dto.liga;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String ubicacion;
    private boolean activa;
    private int totalTemporadas;
    private int totalResponsables;
}
