package com.futvia.model.equipo;

import com.futvia.model.auth.Usuario;
import com.futvia.model.liga.CategoriaCompetencia;
import com.futvia.model.liga.Liga;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String escudoUrl;

    private String colorPrimario;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Jugador> jugadores;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaCompetencia categoria;

    // 📞 Información de contacto
    private String contacto;

    // 👤 Encargado del equipo (usuario del sistema)
    private String encargado;

}
