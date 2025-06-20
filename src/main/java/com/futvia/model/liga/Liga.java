package com.futvia.model.liga;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private String ubicacion;

    private boolean activa;

    // Relación con usuarios tipo LIGA_MANAGER
    @OneToMany(mappedBy = "ligaAsignada")
    private Set<com.futvia.model.auth.Usuario> responsables;



    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL)
    private Set<Temporada> temporadas;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL)
    private Set<Reglamento> reglamentos;
    // Otros atributos que podrías agregar más adelante:
    // @OneToMany(mappedBy = "liga") Set<Equipo> equipos;
}
