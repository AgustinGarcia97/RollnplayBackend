package com.example.serverapi.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
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
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Listing> listings;
    @ManyToOne
    @JoinTable(name="listing_id")
    private Listing Listing;

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(Listing l) {
        this.listings.add(l);
    }

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductStock() {
        return productStock;
    }

    public void setProductStock(double productStock) {
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory='" + productCategory + '\'' +
                ", productStock=" + productStock +
                '}';
    }
}
