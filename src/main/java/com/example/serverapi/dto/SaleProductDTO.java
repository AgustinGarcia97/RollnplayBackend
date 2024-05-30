package com.example.serverapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleProductDTO {
    private Long sale_id;
    private Long product_id;
    private Integer quantity;
}
