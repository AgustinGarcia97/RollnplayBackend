package com.example.serverapi.dto;

import com.example.serverapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public ProductDTO(Long productId, String productName,
                      String productDescription,CategoryDTO productCategory,
                      PlayerDTO productPlayers, BrandDTO productBrand,
                      DurationDTO durationDTO, DifficultyDTO difficultyDTO){
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productPlayers = productPlayers;
        this.productBrand = productBrand;
        this.durationDTO = durationDTO;
        this.difficultyDTO = difficultyDTO;

    }


}
