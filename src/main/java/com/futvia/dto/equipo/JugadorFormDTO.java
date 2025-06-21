package com.futvia.dto.equipo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JugadorFormDTO {
    private String nombre;
    private String apellido;
    private String posicion;
    private Integer dorsal;
    private LocalDate fechaNacimiento;
    private String pais;
    private String telefono;
    private String email;
    private String fotoUrl;
    private Double peso;
    private Double altura;
    private boolean lesionado;
    private String biografia;
    private boolean activoPublico;
    private String videosDestacados;
    private String premios;
    private String trayectoria;
    private boolean destacadoDelMes;
    private Long equipoId;
    private Long usuarioId;
}
