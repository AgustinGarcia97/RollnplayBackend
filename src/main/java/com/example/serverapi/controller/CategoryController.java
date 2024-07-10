package com.example.serverapi.controller;


import com.example.serverapi.database.service.CategoryService;
import com.example.serverapi.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-categories")
    public ResponseEntity<?> getCategories() {
        try{
            List<CategoryDTO> categoriesDTO = categoryService.getAllCategories();
            return ResponseEntity.ok(categoriesDTO);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
