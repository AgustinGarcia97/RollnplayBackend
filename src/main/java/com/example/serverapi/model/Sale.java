package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private long saleId;
    @Column(name="sale_date")
    private LocalDateTime saleDate;

    @Column(name="quantity_products")
    private int quantityProducts;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name="sale_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> productsSales;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "sale_listing",
            joinColumns = @JoinColumn(name="sale_id"),
            inverseJoinColumns = @JoinColumn(name="listing_id")
    )
    private List<Listing> listingsSales;


    public void setListingsSales(Listing listing) {
        listingsSales.add(listing);
    }

    public void setProductSales(Product product) {
        productsSales.add(product);

    }



}
