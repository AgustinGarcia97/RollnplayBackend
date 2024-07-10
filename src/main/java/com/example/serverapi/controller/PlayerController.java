package com.example.serverapi.controller;

import com.example.serverapi.database.service.PlayerService;
import com.example.serverapi.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/get-all-players")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        try{
            List<PlayerDTO> players = playerService.getAllPlayers();
            return ResponseEntity.ok(players);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }
}
