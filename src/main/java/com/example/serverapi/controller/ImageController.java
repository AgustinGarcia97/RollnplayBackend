package com.example.serverapi.controller;


import com.example.serverapi.database.service.ImageService;
import com.example.serverapi.dto.ImageDTO;
import com.example.serverapi.model.Image;
import com.example.serverapi.utils.DTOConverter;
import com.example.serverapi.utils.converter.DtoAssembler;
import com.example.serverapi.validator.ListingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageController {

    private final ImageService imageService;


    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;

    }

    @GetMapping("/get-image")
    public ResponseEntity<ImageDTO> getImage(@RequestParam Long id) {
        try{
            ImageDTO imageDTO = imageService.getImageDTOById(id);
            return new ResponseEntity<>(imageDTO, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-image")
    public ResponseEntity<ImageDTO> createImage(@RequestBody ImageDTO imageDTO) {
        try{
            imageService.createOrUpdateImage(imageDTO);
            return new ResponseEntity<>(imageDTO, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-image")
    public ResponseEntity<ImageDTO> updateImage(@RequestBody ImageDTO imageDTO) {
        try{
            imageService.createOrUpdateImage(imageDTO);
            return new ResponseEntity<>(imageDTO, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-image")
    public ResponseEntity<ImageDTO> deleteImage(@RequestBody Long id) {
        try{
            imageService.deleteImageById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
