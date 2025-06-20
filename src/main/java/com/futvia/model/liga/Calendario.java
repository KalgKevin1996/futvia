package com.futvia.model.liga;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int jornada;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "temporada_id")
    private Temporada temporada;
}
