package com.example.serverapi.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="user_id")
    private String userId;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String password;
    @Column(unique=true,nullable=false)
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
    private List<Listing> listings;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(Listing l) {
        this.listings.add(l);
    }

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

    public User() {
    }

    public String getUserId() {
        return userId;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        isSeller = seller;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isSeller=" + isSeller +
                ", document='" + document + '\'' +
                ", listings=" + listings +
                '}';
    }
}
