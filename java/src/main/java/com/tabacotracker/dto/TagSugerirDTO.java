package com.tabacotracker.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class TagSugerirDTO {
    private UUID marcaId;
    private String nome;
}
