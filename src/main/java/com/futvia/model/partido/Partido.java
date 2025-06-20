package com.futvia.model.partido;

import com.futvia.model.equipo.Equipo;
import com.futvia.model.liga.CategoriaCompetencia;
import com.futvia.model.liga.Temporada;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private EstadoPartido estado;

    private String ubicacion;

    private Integer golesLocal;
    private Integer golesVisitante;

    @ManyToOne
    @JoinColumn(name = "equipo_local_id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visitante_id")
    private Equipo equipoVisitante;

    @ManyToOne
    @JoinColumn(name = "temporada_id")
    private Temporada temporada;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EstadisticaPartido> estadisticas;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IncidenciaPartido> incidencias;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaCompetencia categoria;

}
