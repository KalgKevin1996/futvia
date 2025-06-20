package com.futvia.model.liga;

import com.futvia.model.equipo.Equipo;
import com.futvia.model.partido.Partido;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCompetencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;     // Ej: "Juvenil A", "Libre B", "Femenina"

    private String descripcion;

    private String diaJuego;   // Ej: "Sábado", "Domingo", "Entre semana"

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    @OneToMany(mappedBy = "categoria")
    private Set<Equipo> equipos;

    @OneToMany(mappedBy = "categoria")
    private Set<Partido> partidos;
}
