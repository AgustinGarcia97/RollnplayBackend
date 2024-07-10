package com.example.serverapi.database.repository;

import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.model.Product;
import com.example.serverapi.dto.CategoryDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



    Product findByProductName(String productName);

    @Query("SELECT new com.example.serverapi.dto.ProductDTO(" +
            "   p.productId, " +
            "   p.productName, " +
            "   p.productDescription, " +
            "   new com.example.serverapi.dto.CategoryDTO(p.category.categoryId, p.category.categoryName), " +
            "   new com.example.serverapi.dto.PlayerDTO(p.players.playersId, p.players.numberOfPlayers), " +
            "   new com.example.serverapi.dto.BrandDTO(p.productBrand.brandName, p.productBrand.brandId), " +
            "   new com.example.serverapi.dto.DifficultyDTO(p.difficulty.id, p.difficulty.difficultyName), " +
            "   new com.example.serverapi.dto.DurationDTO(p.duration.id, p.duration.durationName)" +
            ") " +
            "FROM Product p " +
            "WHERE p.productId = :productId")
    ProductDTO findByProductIdCustom(Long productId);



}
