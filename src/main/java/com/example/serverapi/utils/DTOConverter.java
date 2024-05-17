package com.example.serverapi.utils;

import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.dto.PurchaseDTO;
import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.model.*;

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
                .map(this::convertToListingDTO)
                .collect(Collectors.toList()));

        userDTO.setPurchasesDTO(user.getPurchases());
        return userDTO;
    }

    public ListingDTO convertToListingDTO(Listing listing) {
        ListingDTO listingDTO = new ListingDTO();
        listingDTO.setListingId(listing.getListingId());
        listingDTO.setTitle(listing.getTitle());
        listingDTO.setDescription(listing.getDescription());
        listingDTO.setPrice(listing.getPrice());
        listingDTO.setStock(listing.getStock());
        listingDTO.setProductDTO(listing.getProduct().getProductId());

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

    public PurchaseDTO convertToPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setPurchaseId(purchase.getPurchaseId());
        purchaseDTO.setUserId(purchase.getUser().getUserId());
        purchaseDTO.setPurchaseDateTime(purchase.getPurchaseDateTime());
        purchaseDTO.setListingIds(
                purchase.getListingsPurchases()
                        .stream()
                        .map(Listing::getListingId)
                        .collect(Collectors.toList())

        );
        purchaseDTO.setProductIds(
                purchase.getProductsPurchases()
                        .stream()
                        .map(Product::getProductId)
                        .collect(Collectors.toList())
        );
        return purchaseDTO;
    }

    public ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductCategory(product.getProductCategory());

        return productDTO;
    }


}
