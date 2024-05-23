package com.example.serverapi.database.repository;

import com.example.serverapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
   Category findFirstByCategoryName(String categoryName);
}
