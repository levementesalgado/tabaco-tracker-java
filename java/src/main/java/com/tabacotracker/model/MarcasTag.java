package com.tabacotracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "marcas_tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarcasTag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID marcaId, tagId, sugeridoPor;

    @Builder.Default
    private String status = "pending";

    @Builder.Default
    private Integer totalUpvotes = 0, totalDownvotes = 0;
}
