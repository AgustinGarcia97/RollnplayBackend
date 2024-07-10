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
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="product")
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;
    @Column(name="product_name",nullable=false, unique=true)
    private String productName;
    @Column(name="product_description",nullable=false)
    private String productDescription;
    @Column(name="product_price",nullable=false)
    private double productPrice;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany(mappedBy = "productsPurchases",cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Purchase> purchase;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Listing> listings;

    @ManyToOne( fetch = FetchType.LAZY,cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    private Player players;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Brand productBrand;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Difficulty difficulty;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Duration duration;

    public Product(String productName, String productDescription, double productPrice,  double productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.listings = new ArrayList<>();
    }

    public Product() {
    }



    public void setListings(Listing l) {
        this.listings.add(l);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(productPrice, product.productPrice) == 0 && Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && Objects.equals(productDescription, product.productDescription) && Objects.equals(category, product.category) && Objects.equals(purchase, product.purchase) && Objects.equals(listings, product.listings) && Objects.equals(players, product.players) && Objects.equals(productBrand, product.productBrand) && Objects.equals(difficulty, product.difficulty) && Objects.equals(duration, product.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productDescription, productPrice, category, purchase, listings, players, productBrand, difficulty, duration);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productId=" + productId +
                '}';
    }
}
