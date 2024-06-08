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
    private UUID userId;
    private ProductDTO productDT0;
    private ListingDTO listingDTO;
    private int productsQuantity;

}
