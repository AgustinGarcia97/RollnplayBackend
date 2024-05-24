package com.example.serverapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private long imagesId;
    private String imageUrl;
    private long listingId;

    public ImageDTO( String imageUrl, long listingId) {
        this.imageUrl = imageUrl;
        this.listingId = listingId;
    }


}
