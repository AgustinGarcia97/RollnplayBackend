package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.CategoryDTO;
import com.example.serverapi.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category convertToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        if(categoryDTO.getCategoryId() != null){
            category.setCategoryId(categoryDTO.getCategoryId());
        }
        category.setCategoryName(categoryDTO.getCategoryName());

        return category;
    }
}
