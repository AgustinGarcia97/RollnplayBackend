package com.example.serverapi.dto;

import com.example.serverapi.model.Listing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private Long imagesId;
    private String imageUrl;
    private Long listingId;

    public ImageDTO( String imageUrl) {
        this.imageUrl = imageUrl;

    }


}
