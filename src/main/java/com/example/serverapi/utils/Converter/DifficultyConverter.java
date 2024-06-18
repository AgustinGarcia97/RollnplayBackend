package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.DifficultyDTO;
import com.example.serverapi.model.Difficulty;
import org.springframework.stereotype.Component;

@Component
public class DifficultyConverter {


    public Difficulty convertToEntity(DifficultyDTO difficultyDTO){
        Difficulty difficulty = new Difficulty();
        try{
            if(difficultyDTO.getId() != null){
                difficulty.setId(difficultyDTO.getId());
            }
            difficulty.setDifficultyName(difficultyDTO.getDifficultyName());
        } catch(Exception e){
            e.printStackTrace();
        }
        return difficulty;
    }

    public DifficultyDTO convertToDTO(Difficulty difficulty){
        DifficultyDTO difficultyDTO = new DifficultyDTO();
        try{
            if(difficulty.getId() != null){
                difficultyDTO.setId(difficulty.getId());
                }
            difficultyDTO.setDifficultyName(difficulty.getDifficultyName());
        } catch(Exception e){
            e.printStackTrace();
        }
        return difficultyDTO;

    }
}
