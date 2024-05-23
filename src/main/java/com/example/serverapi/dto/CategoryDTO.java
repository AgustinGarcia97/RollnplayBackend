package com.example.serverapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private List<Long> productsIds;

    public CategoryDTO(Long categoryId, String categoryName ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;

    }
}
