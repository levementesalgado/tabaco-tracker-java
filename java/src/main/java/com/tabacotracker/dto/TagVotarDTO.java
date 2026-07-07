package com.tabacotracker.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class TagVotarDTO {
    private UUID marcasTagId;
    private Integer voto;
}
