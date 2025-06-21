package com.futvia.dto.comunidad;

import com.futvia.model.comunidad.TipoReaccion;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaccionDTO {
    private Long id;
    private TipoReaccion tipo;
    private String autorNombre;
    private Long publicacionId; // puede ser null
    private Long comentarioId;  // puede ser null
}
