package com.futvia.model.liga;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reglamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000)
    private String contenido;

    private boolean publico;

    private String version;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;
}
