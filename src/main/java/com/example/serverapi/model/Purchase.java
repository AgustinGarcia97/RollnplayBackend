package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="purchase_id")
    private long purchaseId;
    @Column(name="purchase_date_time")
    private LocalDateTime purchaseDateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "purchase_product",
            joinColumns = @JoinColumn(name="purchase_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> productsPurchases;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "purchase_listing",
            joinColumns = @JoinColumn(name="purchase_id"),
            inverseJoinColumns = @JoinColumn(name="listing_id")
    )
    private List<Listing> listingsPurchases;





}
