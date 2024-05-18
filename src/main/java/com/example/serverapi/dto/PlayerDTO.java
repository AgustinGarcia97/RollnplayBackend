package com.example.serverapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PlayerDTO {
    private Long playerId;
    private String numberOfPlayers;
    private List<Long> productsId;


}
