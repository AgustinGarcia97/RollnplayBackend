package com.example.serverapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private CategoryDTO productCategory;
    private PlayerDTO productPlayers;
    private BrandDTO productBrand;
    private DurationDTO durationDTO;
    private DifficultyDTO difficultyDTO;
    private String duration;
    private String difficulty;
    private String categoryName;
    private String playerCounter;
    private String brandName;
    private double productPrice;
}
