package com.example.serverapi.database.repository;

import com.example.serverapi.model.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE SaleProduct sp SET sp.quantity = :quantity WHERE sp.saleId = :saleId AND sp.productId = :productId")
    void updateProduct(Integer saleId, Long productId, Integer quantity);

    @Transactional
    @Modifying
    @Query("DELETE SaleProduct sp WHERE sp.saleId = :saleId AND sp.productId = :productId")
    void deleteSaleProductBySaleAndProduct(Integer saleId, Long productId);



}
