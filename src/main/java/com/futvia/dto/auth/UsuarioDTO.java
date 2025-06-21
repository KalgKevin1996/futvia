package com.futvia.dto.auth;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private boolean activo;
    private Set<String> roles; // Solo nombres, como "ADMIN", "JUGADOR"
    private Long ligaAsignadaId; // Si aplica
}
