package com.example.serverapi.dto;

import com.example.serverapi.model.Listing;
import com.example.serverapi.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data

@NoArgsConstructor
public class UserDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
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
    private String role;

    public UserDTO(UUID userId, String firstName, String lastName, String email, String address, String phoneNumber, boolean isSeller, String document, Role role, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isSeller = isSeller;
        this.document = document;
        this.role = String.valueOf(role);
        this.password = password;
    }
    public boolean getIsSeller(){
        return this.isSeller;
    }


}
