package com.tabacotracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "avaliacoes", uniqueConstraints = @UniqueConstraint(columnNames = {"marca_id", "usuario_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID marcaId, usuarioId;

    @Column(nullable = false)
    private Integer nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    private Double preco;
    private String regiao;
    private Instant createdAt;

    @PrePersist
    void onCreate() { createdAt = Instant.now(); }
}
