package com.futvia.dto.equipo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JugadorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String posicion;
    private Integer dorsal;
    private String fotoUrl;
    private String equipoNombre;
    private boolean destacadoDelMes;
    private boolean activoPublico;
}
