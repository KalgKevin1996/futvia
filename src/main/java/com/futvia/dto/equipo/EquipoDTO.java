package com.futvia.dto.equipo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoDTO {
    private Long id;
    private String nombre;
    private String escudoUrl;
    private String colorPrimario;
    private String ligaNombre;
    private String categoriaNombre;
}
