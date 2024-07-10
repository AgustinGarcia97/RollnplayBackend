package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private Long saleId;

    @Column(name="sale_date")
    private LocalDateTime saleDate;

    @Column(name="quantity_products")
    private int quantityProducts;

    private double price;

    @ManyToMany
    @JoinTable(
            name = "sale_seller",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "sale_listing",
            joinColumns = @JoinColumn(name="sale_id"),
            inverseJoinColumns = @JoinColumn(name="listing_id")
    )
    private List<Listing> listingsSales;

    @ElementCollection
    @CollectionTable(
            name = "sale_listing_quantity",
            joinColumns = @JoinColumn(name = "sale_id")
    )
    @MapKeyJoinColumn(name = "listing_id")
    @Column(name = "quantity")
    private Map<Long, Double> listingQuantity = new HashMap<>();

    public void setListingsSales(Listing listing) {
        if(listingsSales == null) {
            listingsSales = new ArrayList<>();
        }
        listingsSales.add(listing);
    }


    public void addListingQuantity(Listing listing) {
        Long listingId = listing.getListingId();
        double quantity = listing.getQuantity();

        if (listingQuantity.containsKey(listingId)) {
            double currentQuantity = listingQuantity.get(listingId);
            listingQuantity.put(listingId, currentQuantity + quantity);
        } else {

            listingQuantity.put(listingId, quantity);
        }
    }

    public double getListingQuantity(Long listingId) {
        return listingQuantity.get(listingId) != null? listingQuantity.get(listingId): 0;
    }





}
