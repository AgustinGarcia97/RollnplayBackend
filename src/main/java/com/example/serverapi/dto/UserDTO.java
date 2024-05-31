package com.example.serverapi.dto;

import com.example.serverapi.model.Listing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data

@NoArgsConstructor
public class UserDTO {
    private UUID userId;
    private String name;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private String document;
    private String mail;
    private List<ListingDTO> listingsDTO;
    private List<SaleDTO> salesDTO;
    private List<PurchaseDTO> purchasesDTO;
    private boolean isSeller;

    public boolean getIsSeller(){
        return this.isSeller;
    }


}
