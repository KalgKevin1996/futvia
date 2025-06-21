package com.futvia.dto.comunidad;

import com.futvia.model.comunidad.TipoReaccion;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaccionFormDTO {
    private TipoReaccion tipo;
    private Long publicacionId;
    private Long comentarioId;
}
