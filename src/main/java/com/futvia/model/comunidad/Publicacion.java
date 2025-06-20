package com.futvia.model.comunidad;

import com.futvia.model.auth.Usuario;
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
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    private String imagenUrl;

    private LocalDateTime fecha;

    private boolean visible;

    @Enumerated(EnumType.STRING)
    private EstadoModeracion estadoModeracion;


    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaPublicacion categoria;

}
