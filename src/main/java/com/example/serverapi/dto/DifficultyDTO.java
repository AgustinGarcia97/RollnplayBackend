package com.example.serverapi.dto;

import com.example.serverapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DifficultyDTO {
    private Long id;
    private String difficultyName;
}
