package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.PlayerDTO;
import com.example.serverapi.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerConverter {

    public Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        if(playerDTO.getPlayerId() != null){
            player.setPlayersId(playerDTO.getPlayerId());
        }
        player.setNumberOfPlayers(playerDTO.getNumberOfPlayers());

        return player;
    }
}
