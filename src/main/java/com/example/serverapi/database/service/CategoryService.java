package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.CategoryRepository;
import com.example.serverapi.dto.CategoryDTO;
import com.example.serverapi.model.Category;
import com.example.serverapi.utils.converter.DtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DtoAssembler dtoAssembler;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, DtoAssembler dtoAssembler) {
        this.categoryRepository = categoryRepository;
        this.dtoAssembler = dtoAssembler;
    }

    public Category createOrUpdateCategory(CategoryDTO categoryDTO) {
        Category category = null;
        try{
            category = dtoAssembler.getCategoryEntity(categoryDTO);
            categoryRepository.save(category);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

}
