package com.example.serverapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Listing listing;



}
