package com.tabacotracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "registros_diarios", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "data"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID usuarioId;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDate data;

    @PrePersist
    void onCreate() { if (data == null) data = LocalDate.now(); }
}
