package com.tabacotracker.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class MarcaDTO {
    private String nome, variedade, fabricante, fotoUrl;
}
