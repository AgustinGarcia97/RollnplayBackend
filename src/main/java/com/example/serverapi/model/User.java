package com.example.serverapi.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Builder
@Table(name="user")
@AllArgsConstructor
@Data
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="user_id")
    private UUID userId;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false, name="first_name")
    private String firstName;
    @Column(nullable=false, name="last_name")
    private String lastName;
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Listing> listings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Sale> sales;


    public User(String username, String firstName, String password, String email, String address, String phoneNumber, String document, boolean isSeller) {

        this.username = username;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.document = document;
        this.listings = new ArrayList<>();
        this.isSeller = isSeller;

    }

    public User() {
    }

    public void setListings(List<Listing> listings) {
        if(this.listings == null) {
            listings = new ArrayList<>();
        }
        this.listings = listings;
    }

    public void setPurchases(List<Purchase> purchases) {
        if(this.purchases == null) {
            purchases = new ArrayList<>();
        }
        this.purchases = purchases;
    }

    public void setSales(List<Sale> sales) {
        if(this.sales == null) {
            sales = new ArrayList<>();
        }
        this.sales = sales;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    public boolean getIsSeller(){
        return this.isSeller;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
