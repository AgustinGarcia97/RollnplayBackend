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
    @Column(name="image_url", columnDefinition = "LONGTEXT")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Listing listing;



}
