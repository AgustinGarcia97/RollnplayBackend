package com.example.serverapi.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="listing")
@Data
public class Listing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="listing_id")
    private Long listingId;
    private String title;
    private String description;
    private double stock;
    private double price;
    private boolean state;
    @ManyToOne(fetch = FetchType.LAZY,cascade =  {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch =  FetchType.LAZY,cascade =  {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="product_id")
    @JsonBackReference
    private Product product;

    @ManyToMany(mappedBy="listingsPurchases", fetch = FetchType.LAZY,cascade =  {CascadeType.MERGE, CascadeType.PERSIST} )
    private List<Purchase> purchases;

    @ManyToMany(mappedBy="listingsSales", fetch = FetchType.LAZY, cascade =  {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Sale> sales;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    public Listing() {
    }

    public Listing( String title, String description, double price, double stock) {
        this.title = title;
       this.description = description;
       this.price = price;
       this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Product getProduct() {
        return product;
    }







}


