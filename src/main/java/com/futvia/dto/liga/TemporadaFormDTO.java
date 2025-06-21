package com.futvia.dto.liga;

import com.futvia.model.liga.FormatoLiga;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemporadaFormDTO {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private FormatoLiga formato;
    private boolean activa;
    private Long ligaId;
}
