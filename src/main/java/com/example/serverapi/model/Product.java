package com.example.serverapi.model;

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
    @Column(name="product_price",nullable=false)
    private double productPrice;
    @Column(name="product_category",nullable=false)
    private String productCategory; //categoria
    @Column(name="product_stock",nullable=false)
    private double productStock;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> categories;

    @ManyToMany(mappedBy = "productsSales", fetch = FetchType.LAZY)
    private List<Sale> sales;

    @ManyToMany(mappedBy = "productsPurchases", fetch = FetchType.LAZY)
    private List<Purchase> purchase;


    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Listing> listings;

    @OneToMany(mappedBy="product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Player> playerList;



    public Product(String productName, String productDescription, double productPrice, String productCategory, double productStock) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productStock = productStock;
        this.listings = new ArrayList<>();
    }

    public Product() {
    }

    public void setListings(Listing l) {
        this.listings.add(l);
    }

}
