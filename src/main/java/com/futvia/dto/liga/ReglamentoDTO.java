package com.futvia.dto.liga;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReglamentoDTO {
    private Long id;
    private String contenido;
    private boolean publico;
    private String version;
    private String ligaNombre;
}
