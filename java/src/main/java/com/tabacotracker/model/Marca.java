package com.tabacotracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "marcas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String slug;

    private String variedade, fabricante, fotoUrl;

    @Column(nullable = false)
    @Builder.Default
    private String status = "pending";

    @Column(nullable = false)
    private UUID criadoPor;

    @Builder.Default
    private Integer reportCount = 0;

    private Instant createdAt, updatedAt;

    @PrePersist
    void onCreate() { createdAt = updatedAt = Instant.now(); }

    @PreUpdate
    void onUpdate() { updatedAt = Instant.now(); }
}
