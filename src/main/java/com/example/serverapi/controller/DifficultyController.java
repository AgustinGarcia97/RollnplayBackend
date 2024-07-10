package com.example.serverapi.controller;


import com.example.serverapi.database.service.DifficultyService;
import com.example.serverapi.dto.DifficultyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DifficultyController {

    private final DifficultyService difficultyService;

    @Autowired
    public DifficultyController(DifficultyService difficultyService) {
        this.difficultyService = difficultyService;
    }


    @GetMapping("/get-all-difficulties")
    public ResponseEntity<?> getAllDifficulties() {
        try{
            List<DifficultyDTO> difficultyDTOS = difficultyService.getAllDifficultiesDto();
            return ResponseEntity.ok(difficultyDTOS);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
