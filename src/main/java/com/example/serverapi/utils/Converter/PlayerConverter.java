package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.PlayerDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerConverter {

    public Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        try {
            if (playerDTO.getPlayerId() != null) {
                player.setPlayersId(playerDTO.getPlayerId());
            }
            player.setNumberOfPlayers(playerDTO.getNumberOfPlayers());
        }
        catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return player;
    }

    public PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        try{
            playerDTO.setPlayerId(player.getPlayersId());
            playerDTO.setNumberOfPlayers(player.getNumberOfPlayers());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return playerDTO;
    }
}
