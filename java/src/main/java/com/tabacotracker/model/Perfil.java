package com.tabacotracker.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Perfil {
    @Id
    private UUID id;

    private String nome;
    private Integer idade;
    private String genero, regiao;
    private Integer mediaCigarrosDia;

    @Builder.Default
    private Boolean optInLeaderboard = false;
}
