package com.example.serverapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private String brandName;
    private Long brandId;
    private List<ProductDTO> products;
    private List<ListingDTO> listings;
}
