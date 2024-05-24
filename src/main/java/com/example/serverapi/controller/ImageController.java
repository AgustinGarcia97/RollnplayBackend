package com.example.serverapi.controller;


import com.example.serverapi.database.service.ImageService;
import com.example.serverapi.dto.ImageDTO;
import com.example.serverapi.model.Image;
import com.example.serverapi.utils.DTOConverter;
import com.example.serverapi.validator.ListingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private ImageService imageService;

    @DeleteMapping("/delete-photo")
    public ResponseEntity<String> deletePhoto(@RequestParam List<ImageDTO> imageDTO) {

        try{

            for(ImageDTO imageDTO1: imageDTO){
                Image image = dtoConverter.convertToImage(imageDTO1);
                imageService.deleteImage(image);
            }

            return new ResponseEntity<>("Imagenes eliminadas correctamente", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
