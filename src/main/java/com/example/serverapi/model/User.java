package com.example.serverapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int userId;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String address;
    @Column(name="phone_number",nullable=false)
    private String phoneNumber;
    @Column(nullable=false, name="is_seller")
    private boolean isSeller;
    @Column(nullable=false)
    private String document;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Listing> listings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Sale> sales;


    public User(String username, String name, String password, String email, String address, String phoneNumber, String document, boolean isSeller) {

        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.document = document;
        this.listings = new ArrayList<>();
        this.isSeller = isSeller;

    }





    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }


}
