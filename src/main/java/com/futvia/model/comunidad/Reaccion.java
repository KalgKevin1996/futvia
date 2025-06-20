package com.futvia.model.comunidad;

import com.futvia.model.auth.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoReaccion tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = true)
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "comentario_id", nullable = true)
    private Comentario comentario;
}
