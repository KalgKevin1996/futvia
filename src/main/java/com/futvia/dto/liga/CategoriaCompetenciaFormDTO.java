package com.futvia.dto.liga;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCompetenciaFormDTO {
    private String nombre;
    private String descripcion;
    private String diaJuego;
    private Long ligaId;
}
