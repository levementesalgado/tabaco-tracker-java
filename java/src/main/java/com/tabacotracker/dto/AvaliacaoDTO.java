package com.tabacotracker.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class AvaliacaoDTO {
    private UUID marcaId;
    private Integer nota;
    private String comentario;
    private Double preco;
    private String regiao;
}
