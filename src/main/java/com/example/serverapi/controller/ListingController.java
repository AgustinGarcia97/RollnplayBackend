package com.example.serverapi.controller;

import com.example.serverapi.database.service.ListingService;
import com.example.serverapi.dto.CategoryDTO;
import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.dto.PlayerDTO;
import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.exceptions.ListingValidationException;
import com.example.serverapi.model.Listing;
import com.example.serverapi.utils.DTOConverter;
import com.example.serverapi.validator.ListingValidator;
import com.example.serverapi.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ListingController {
    @Autowired
    private ListingService listingService;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private ListingValidator listingValidator;


    @PostMapping("/create-listing")
    public ResponseEntity<String> createListing(@RequestBody ListingDTO listingDTO1) {
        ListingDTO listingDTO = new ListingDTO(
                "Vendo algun juego random",
                "Descripcion de la venta random",
                10,
                999.99,
                UUID.fromString("7c72d9f4-3bc0-4b21-9216-65f3ad2dde2b"),
                new ProductDTO("Monopoly","juego de mesa",
                        new CategoryDTO(0L,"familiar"),
                        new PlayerDTO(0L, "2 a 4 jugadores"))
        );
        Listing listing;
        try{
            listingValidator.validateListing(listingDTO);
            listing = dtoConverter.convertToListing(listingDTO);

        }
        catch(ListingValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        listingService.createListing(listing);
        return new ResponseEntity<>("Publicacion creada correctamente", HttpStatus.CREATED);
    }
}
