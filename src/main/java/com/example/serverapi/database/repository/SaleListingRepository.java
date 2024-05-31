package com.example.serverapi.database.repository;

import com.example.serverapi.model.SaleListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SaleListingRepository extends JpaRepository<SaleListing, Long> {

    @Query("SELECT COUNT(l) FROM Listing l WHERE l.listingId = :listing_id AND l.product.productId = :product_id")
    Integer countByListingAndProduct(Long listing_id, Long product_id);

    @Procedure(name="sp_product_and_listing")
    void sp_product_and_listing(@Param("sale_id") Integer sale_id, @Param("product_id") Long product_id, @Param("quantity") Integer quantity, @Param("listing_id") Long listing_id);

}
