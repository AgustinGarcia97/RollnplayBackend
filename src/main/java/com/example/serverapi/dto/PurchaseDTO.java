package com.example.serverapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
public class PurchaseDTO {
    private Long purchaseId;
    private LocalDateTime purchaseDateTime;
    private UUID userId;
    private List<Long> productIds;
    private List<Long> listingIds;

}
