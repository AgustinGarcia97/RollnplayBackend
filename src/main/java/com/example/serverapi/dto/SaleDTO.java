package com.example.serverapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class SaleDTO {
    private Long saleId;
    private LocalDateTime saleDate;
    private UUID seller;
    private UUID buyer;
    private String sellerName;
    private String buyerName;
    private List<ListingDTO> listingsDTO;

    private double price;

    private UUID userId;
    private ListingDTO listingDTO;



}
