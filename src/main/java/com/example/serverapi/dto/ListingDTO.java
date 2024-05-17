package com.example.serverapi.dto;


import com.example.serverapi.model.Product;
import com.example.serverapi.model.Purchase;
import com.example.serverapi.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ListingDTO {
    private Long listingId;
    private String title;
    private String description;
    private double stock;
    private double price;
    private UUID userId;
    private ProductDTO productDTO;
    private List<Long> salesId;
    private List<Long> purchaseId;
}
