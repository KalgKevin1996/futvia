package com.futvia.dto.comunidad;

import com.futvia.model.comunidad.EstadoModeracion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComentarioDTO {
    private Long id;
    private String contenido;
    private LocalDateTime fecha;
    private EstadoModeracion estadoModeracion;
    private String autorNombreCompleto; // nombre + apellido o solo nombre
    private Long publicacionId;
}
