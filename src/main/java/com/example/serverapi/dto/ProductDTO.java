package com.example.serverapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private List<CategoryDTO> categories;
    private List<PlayerDTO> playerList;
    private List<Long> listingIds;
    private List<Long> salesIds;
    private List<Long> purchaseIds;


}
