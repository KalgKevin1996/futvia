package com.futvia.dto.partido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenciaPartidoFormDTO {
    private String tipo;
    private String descripcion;
    private Integer minuto;
    private Long partidoId;
}
