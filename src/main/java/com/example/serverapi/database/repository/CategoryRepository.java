package com.example.serverapi.database.repository;

import com.example.serverapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
   Category findFirstByCategoryName(String categoryName);
}
