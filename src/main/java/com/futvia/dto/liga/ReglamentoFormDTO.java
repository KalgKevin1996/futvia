package com.futvia.dto.liga;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReglamentoFormDTO {
    private String contenido;
    private boolean publico;
    private String version;
    private Long ligaId;
}
