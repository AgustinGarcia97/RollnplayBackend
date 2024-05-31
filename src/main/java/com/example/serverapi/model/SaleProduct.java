package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sale_product", uniqueConstraints = @UniqueConstraint(columnNames = {"sale_id", "product_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleProduct {
    @Id
    @Column(name="sale_id")
    private long saleId;

    @Column(name="product_id")
    private Long productId;

    @Column(name="quantity")
    private Integer quantity;



}
