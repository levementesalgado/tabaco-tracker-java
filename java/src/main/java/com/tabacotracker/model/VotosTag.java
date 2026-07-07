package com.tabacotracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "votos_tags", uniqueConstraints = @UniqueConstraint(columnNames = {"marcas_tag_id", "usuario_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotosTag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID marcasTagId, usuarioId;

    @Column(nullable = false)
    private Integer voto;
}
