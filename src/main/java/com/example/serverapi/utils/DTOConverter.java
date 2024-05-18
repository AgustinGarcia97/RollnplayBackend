package com.example.serverapi.utils;

import com.example.serverapi.dto.*;
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

        userDTO.setPurchasesDTO(user.getPurchases()
                .stream()
                .map(this::convertToPurchaseDTO)
                .collect(Collectors.toList())
        );

        userDTO.setSalesDTO(user.getSales()
                .stream()
                .map(this::convertToSaleDTO)
                .collect(Collectors.toList())
        );

        return userDTO;
    }

    public ListingDTO convertToListingDTO(Listing listing) {
        ListingDTO listingDTO = new ListingDTO();
        listingDTO.setListingId(listing.getListingId());
        listingDTO.setTitle(listing.getTitle());
        listingDTO.setDescription(listing.getDescription());
        listingDTO.setPrice(listing.getPrice());
        listingDTO.setStock(listing.getStock());
        listingDTO.setProductDTO(convertToProductDTO(listing.getProduct()));

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
        productDTO.setProductCategory(convertToCategoryDTO(product.getCategory()));
        productDTO.setProductPlayers(convertToPlayersDTO(product.getPlayers()));

        return productDTO;
    }

    public SaleDTO convertToSaleDTO(Sale sale){
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(sale.getSaleId());
        saleDTO.setSaleDate(sale.getSaleDate());
        saleDTO.setListingsId(
                sale.getListingsSales()
                        .stream()
                        .map(Listing::getListingId)
                        .collect(Collectors.toList())
        );
        saleDTO.setProductIds(
                sale.getProductsSales()
                        .stream()
                        .map(Product::getProductId)
                        .collect(Collectors.toList())
        );
        return saleDTO;
    }

    public CategoryDTO convertToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setProductsIds(category.getProducts()
                .stream()
                .map(Product::getProductId)
                .collect(Collectors.toList()));

        return categoryDTO;
    }

    public PlayerDTO convertToPlayersDTO(Player player){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(player.getPlayersId());
        playerDTO.setNumberOfPlayers(player.getNumberOfPlayers());
        playerDTO.setProductsId(player.getProduct()
                .stream()
                .map(Product::getProductId)
                .collect(Collectors.toList()));

        return playerDTO;
    }


}
