package com.futvia.dto.liga;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCompetenciaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String diaJuego;
    private String ligaNombre;
}
