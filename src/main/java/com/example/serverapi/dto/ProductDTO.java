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

    public ProductDTO(long productId,String productName, String productDescription, CategoryDTO productCategory, PlayerDTO productPlayers) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productPlayers = productPlayers;
    }



}
