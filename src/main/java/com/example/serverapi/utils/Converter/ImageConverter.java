package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.ImageDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    public Image convertToEntity(ImageDTO imageDTO){
        Image image = new Image();
        try{
            if(imageDTO.getImagesId() != null){
                image.setId(imageDTO.getImagesId());
            }
            image.setImageUrl(imageDTO.getImageUrl());


        }
        catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());
        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }

        return image;
    }

    public ImageDTO convertToDTO(Image image){
        ImageDTO imageDTO = new ImageDTO();
     try{
        imageDTO.setImageUrl(image.getImageUrl());
        imageDTO.setImagesId(image.getId());
        if(image.getListing().getListingId() != null){
            imageDTO.setListingId(image.getListing().getListingId());
        }


     }
     catch(IllegalArgumentException e){
         System.out.println("values cannot be null:"+e.getMessage());
     }
     catch(ConversionException e){
         System.out.println("conversor error:"+e.getMessage());
     }
     catch(Exception e){
         System.out.println("error:"+e.getMessage());
     }
     return imageDTO;
    }
}
