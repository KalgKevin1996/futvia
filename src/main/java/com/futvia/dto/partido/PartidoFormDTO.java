package com.futvia.dto.partido;

import com.futvia.model.partido.EstadoPartido;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoFormDTO {
    private LocalDateTime fechaHora;
    private EstadoPartido estado;
    private String ubicacion;
    private Integer golesLocal;
    private Integer golesVisitante;
    private Long equipoLocalId;
    private Long equipoVisitanteId;
    private Long temporadaId;
    private Long categoriaId;
}
