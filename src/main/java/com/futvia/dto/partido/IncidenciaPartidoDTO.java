package com.futvia.dto.partido;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenciaPartidoDTO {
    private Long id;
    private String tipo;
    private String descripcion;
    private Integer minuto;
    private Long partidoId;
}
