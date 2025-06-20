package com.futvia.model.equipo;

import com.futvia.model.auth.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String posicion;
    private Integer dorsal;
    private boolean activo;

    private LocalDate fechaNacimiento;
    private String pais;
    private String telefono;
    private String email;
    private String fotoUrl;
    private Double peso;
    private Double altura;
    private boolean lesionado;

    // Estadísticas generales
    private int goles;
    private int asistencias;
    private int partidosJugados;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private int minutosJugados;
    private Double promedioCalificacion;

    // Perfil público y visibilidad
    @Column(length = 1000)
    private String biografia;

    private boolean activoPublico;

    private String videosDestacados;

    // Premios y reconocimientos
    @Column(length = 1000)
    private String premios;

    @Column(length = 1000)
    private String trayectoria;

    private boolean destacadoDelMes;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
