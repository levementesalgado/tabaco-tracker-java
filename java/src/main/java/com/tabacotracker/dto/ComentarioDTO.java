package com.tabacotracker.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ComentarioDTO {
    private UUID avaliacaoId;
    private String texto;
}
