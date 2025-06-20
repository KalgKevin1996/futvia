package com.futvia.model.liga;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Ej: "Apertura 2024"

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private FormatoLiga formato;

    private boolean activa;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    // Puede usarse más adelante
    // @OneToMany(mappedBy = "temporada") private Set<Partido> partidos;
}
