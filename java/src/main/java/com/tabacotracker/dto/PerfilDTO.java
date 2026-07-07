package com.tabacotracker.dto;

import lombok.Data;

@Data
public class PerfilDTO {
    private String nome;
    private Integer idade, mediaCigarrosDia;
    private String genero, regiao;
    private Boolean optInLeaderboard;
}
