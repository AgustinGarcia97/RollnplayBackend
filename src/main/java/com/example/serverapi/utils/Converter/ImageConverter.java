package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.ImageDTO;
import com.example.serverapi.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    @Autowired
    private ListingConverter listingConverter;

    public Image convertToEntity(ImageDTO imageDTO){
        Image image = new Image();
        if(imageDTO.getImagesId() != null) {
            image.setId(imageDTO.getImagesId());
        }
        image.setImageUrl(imageDTO.getImageUrl());
        image.setListing(listingConverter.convertToEntity(imageDTO.getListing()));

        return image;
    }
}
