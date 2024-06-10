package com.example.serverapi.dto;

import com.example.serverapi.model.User;
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
    private UserDTO seller;
    private UUID sellerId;
    private ProductDTO productDTO;
    private ListingDTO ListingDTO;

    private void setListingDTO(ListingDTO listingDTO){
        this.ListingDTO = listingDTO;
    }

}
