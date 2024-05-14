package com.example.serverapi.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name="listing")
public class Listing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="listing_id")
    private String listingId;
    private String title;
    private String productDescription;
    private String description;
    private String seller;
    private double stock;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch =  FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    public Listing() {
    }

    public Listing( String title, String description, double price, double stock) {
        this.title = title;
       this.description = description;
       this.price = price;
       this.stock = stock;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription() {
        if(product != null){
            this.productDescription = getProduct().getProductDescription();
        }
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        if(user != null){
            this.seller = user.getUsername();
        }
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        if(product != null){
            this.stock = product.getProductStock();
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(product != null){
            this.price = product.getProductPrice();
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {

        this.product = product;
        setProductDescription();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String toString() {
        return "Listing{" +
                "listingId='" + listingId + '\'' +
                ", title='" + title + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", description='" + description + '\'' +
                ", seller='" + seller + '\'' +
                ", stock=" + stock +
                ", price=" + price +

                '}';
    }
}


