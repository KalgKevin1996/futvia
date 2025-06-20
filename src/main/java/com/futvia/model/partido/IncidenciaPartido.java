package com.futvia.model.partido;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenciaPartido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;      // Ej: "Gol", "Falta", "Lesión", "Autogol"
    private String descripcion;
    private Integer minuto;

    @ManyToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;
}
