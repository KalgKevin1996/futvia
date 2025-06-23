package com.futvia.dto.equipo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoDTO {
    private Long id;
    private String nombre;
    private String escudoUrl;
    private String colorPrimario;
    private String ligaNombre;
    private String categoriaNombre;
    private String contacto;           // Ej: número o correo
    private String encargado;          // Nombre completo del responsable
}
