package com.futvia.dto.partido;

import com.futvia.model.partido.EstadoPartido;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private EstadoPartido estado;
    private String ubicacion;
    private String equipoLocalNombre;
    private String equipoVisitanteNombre;
    private Integer golesLocal;
    private Integer golesVisitante;
    private String categoriaNombre;
    private String temporadaNombre;
}
