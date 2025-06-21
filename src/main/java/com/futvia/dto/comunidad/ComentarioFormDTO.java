package com.futvia.dto.comunidad;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComentarioFormDTO {
    private String contenido;
    private Long publicacionId;
}
