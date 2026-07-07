package com.tabacotracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "curtidas", uniqueConstraints = @UniqueConstraint(columnNames = {"alvo_id", "alvo_tipo", "usuario_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curtida {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID alvoId, usuarioId;

    @Column(nullable = false)
    private String alvoTipo;
}
