package com.example.serverapi.utils;

import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.dto.PurchaseDTO;
import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.Purchase;
import com.example.serverapi.model.Sale;
import com.example.serverapi.model.User;

import java.util.stream.Collectors;

public class DTOConverter {

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setMail(user.getEmail());
        userDTO.setUsername(user.getUsername());

        userDTO.setListingsDTO(user.getListings()
                .stream()
                .map(this::converToListingDTO)
                .collect(Collectors.toList()));

        userDTO.setPurchasesDTO(user.getPurchases());
        return userDTO;
    }

    public ListingDTO converToListingDTO(Listing listing) {
        ListingDTO listingDTO = new ListingDTO();
        listingDTO.setListingId(listing.getListingId());
        listingDTO.setTitle(listing.getTitle());
        listingDTO.setDescription(listing.getDescription());
        listingDTO.setPrice(listing.getPrice());
        listingDTO.setStock(listing.getStock());
        listingDTO.setProductId(listing.getProduct().getProductId());

        listingDTO.setSalesId(listing.getSales()
                .stream()
                .map(Sale::getSaleId)
                .collect(Collectors.toList())
        );

        listingDTO.setPurchaseId(listing.getPurchases()
                .stream()
                .map(Purchase::getPurchaseId)
                .collect(Collectors.toList())
        );
        return listingDTO;
    }

    public PurchaseDTO converToPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO
    }


}
