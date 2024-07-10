package com.example.serverapi.controller;


import com.example.serverapi.database.service.DurationService;
import com.example.serverapi.dto.DurationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DurationController {

    private final DurationService durationService;

    @Autowired
    public DurationController(DurationService durationService) {
        this.durationService = durationService;
    }

    @GetMapping("/get-all-durations")
    public ResponseEntity<List<DurationDTO>> getAllDurations() {
        try{
            List<DurationDTO> durations = durationService.getALLDurationDTO();
            return ResponseEntity.ok(durations);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
