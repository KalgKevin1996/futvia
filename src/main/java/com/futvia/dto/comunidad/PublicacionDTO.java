package com.futvia.dto.comunidad;

import com.futvia.model.comunidad.EstadoModeracion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicacionDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private String imagenUrl;
    private LocalDateTime fecha;
    private boolean visible;
    private EstadoModeracion estadoModeracion;
    private String autorNombreCompleto;
    private String categoriaNombre;
}
