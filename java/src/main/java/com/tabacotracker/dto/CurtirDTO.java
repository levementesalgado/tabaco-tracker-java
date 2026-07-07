package com.tabacotracker.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CurtirDTO {
    private UUID alvoId;
    private String alvoTipo;
}
