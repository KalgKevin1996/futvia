package com.futvia.dto.comunidad;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicacionFormDTO {
    private String titulo;
    private String contenido;
    private String imagenUrl;
    private Long categoriaId;
    private boolean visible;
}
