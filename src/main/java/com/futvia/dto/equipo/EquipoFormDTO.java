package com.futvia.dto.equipo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoFormDTO {
    private String nombre;
    private String escudoUrl;
    private String colorPrimario;
    private Long ligaId;
    private Long categoriaId;
}
