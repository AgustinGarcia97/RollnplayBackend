package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.DifficultyRepository;
import com.example.serverapi.dto.DifficultyDTO;
import com.example.serverapi.model.Difficulty;
import com.example.serverapi.utils.Converter.DtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;
    private final DtoAssembler dtoAssembler;

    @Autowired
    public DifficultyService(DifficultyRepository difficultyRepository, DtoAssembler dtoAssembler) {
        this.difficultyRepository = difficultyRepository;
        this.dtoAssembler = dtoAssembler;
    }

    public Difficulty createOrUpdateDifficulty(DifficultyDTO difficultyDTO) {
        Difficulty difficulty = null;
        try{
            difficulty = dtoAssembler.getDifficultyEntity(difficultyDTO);
            difficulty = difficultyRepository.save(difficulty);
        } catch (Exception e){
            e.printStackTrace();
        }
        return difficulty;

    }

    public Optional<Difficulty> getDifficultyDTOById(Long id) {
        Optional<Difficulty> difficulty = difficultyRepository.findById(id);
        return difficulty;
    }

    public List<DifficultyDTO> getAllDifficultiesDto(){
        try{
            return difficultyRepository.findAll().stream().map(dtoAssembler::getDifficultyDTO).collect(Collectors.toList());

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
