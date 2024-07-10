package com.example.serverapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.font.TextHitInfo;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private String brandName;
    private Long brandId;
    private List<ProductDTO> products;
    private List<ListingDTO> listings;

    public BrandDTO(String brandName, Long brandId) {
        this.brandName = brandName;
        this.brandId = brandId;
    }
}
