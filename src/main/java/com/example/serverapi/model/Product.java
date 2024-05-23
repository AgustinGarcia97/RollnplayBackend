package com.example.serverapi.model;

import com.example.serverapi.dto.PlayerDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.serverapi.model.Category;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="product")
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;
    @Column(name="product_name",nullable=false)
    private String productName;
    @Column(name="product_description",nullable=false)
    private String productDescription;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany(mappedBy = "productsSales", fetch = FetchType.LAZY)
    private List<Sale> sales;

    @ManyToMany(mappedBy = "productsPurchases", fetch = FetchType.LAZY)
    private List<Purchase> purchase;


    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Listing> listings;

    @ManyToOne( fetch = FetchType.LAZY)
    @JsonManagedReference
    private Player players;



    public Product(String productName, String productDescription, double productPrice,  double productStock) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.listings = new ArrayList<>();
    }

    public Product() {
    }



    public void setListings(Listing l) {
        this.listings.add(l);
    }

}
