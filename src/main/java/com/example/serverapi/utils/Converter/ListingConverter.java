package com.example.serverapi.utils.Converter;


import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ListingConverter {


    private ProductConverter productConverter;


    private UserConverter userConverter;


    private ImageConverter imageConverter;



    public Listing convertToEntity(ListingDTO listingDTO){
        Listing listing = new Listing();
        if(listingDTO.getListingId() != 0){
            listing.setListingId(listingDTO.getListingId());
        }

        listing.setTitle(listing.getTitle());
        listing.setDescription(listingDTO.getDescription());
        listing.setPrice(listingDTO.getPrice());
        listing.setStock(listingDTO.getStock());
        listing.setState(listingDTO.getListingState());
        listing.setProduct(productConverter.convertToEntity(listingDTO.getProductDTO()));
        listing.setUser(userConverter.convertToEntity(listingDTO.getUserDTO()));

        listing.setImages(listingDTO
                .getImages()
                .stream()
                .map(imageDTO -> imageConverter.convertToEntity(imageDTO))
                .collect(Collectors.toList()));



        return listing;
    }
}
