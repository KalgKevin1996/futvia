package com.futvia.dto.auth;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioFormDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String confirmarPassword;
    private boolean activo;
    private Set<Long> rolesIds;
    private Long ligaAsignadaId;
}
