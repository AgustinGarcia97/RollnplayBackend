package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.PlayerRepository;
import com.example.serverapi.dto.PlayerDTO;
import com.example.serverapi.model.Player;
import com.example.serverapi.utils.converter.DtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final DtoAssembler dtoAssembler;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, DtoAssembler dtoAssembler) {
        this.playerRepository = playerRepository;
        this.dtoAssembler = dtoAssembler;
    }

    public Player createOrUpdatePlayer(PlayerDTO playerDTO) {
        Player player = null;
        try{
            player = dtoAssembler.getPlayerEntity(playerDTO);
            playerRepository.save(player);
        }
        catch(Exception e){
            throw e;
        }
        return player;

    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }
}
