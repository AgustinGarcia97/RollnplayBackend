package com.example.serverapi.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.serverapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);
}
