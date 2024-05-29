package com.example.serverapi.controller;

import com.example.serverapi.database.service.ImageService;
import com.example.serverapi.database.service.ListingService;
import com.example.serverapi.database.service.UserService;
import com.example.serverapi.dto.*;
import com.example.serverapi.exceptions.ListingValidationException;
import com.example.serverapi.model.Image;
import com.example.serverapi.model.Listing;
import com.example.serverapi.utils.DTOConverter;
import com.example.serverapi.validator.ListingValidator;
import com.example.serverapi.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


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
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    //cantidad jugadres trae todas los ids de producto -> probablemente sea el DTO de player, lo mismo category/
    @GetMapping("/get-listing")
    public ResponseEntity<?> getListing(@RequestParam long listingId) {
        try{
            Optional<Listing> listing = listingService.getListing(listingId);
            if(listing.isPresent()){
                ListingDTO listingDTO = dtoConverter.convertToListingDTO(listing.get());
                return new ResponseEntity<>(listingDTO, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get-all-listing")
    public ResponseEntity<?> getAllListing() {
        try{
          List<ListingDTO>  listingDTO = listingService
                  .getAllListings().stream()
                  .map(listing -> dtoConverter.convertToListingDTO(listing))
                  .collect(Collectors.toList());

          return new ResponseEntity<>(listingDTO, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //testear -> trae todas las publicaciones del usuario
    @GetMapping("get-listing-user")
    public ResponseEntity<?> getListingUser(@RequestParam UUID userId) {
        try{
            if(userService.getUserById(userId) != null){
                List<Listing> userListings = userService.getListingById(userId);
                return new ResponseEntity<>(userListings, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-listing")
    public ResponseEntity<String> createListing(@RequestBody ListingDTO listingDTO) {
        /*
        ListingDTO listingDTO = new ListingDTO( 0L,
                "Vendo MONOPOLY",
                "Descripcion de la venta de MONOPOLY",
                10,
                999.99,
                UUID.fromString("4ac1ef3b-8ce5-4675-9c12-8d583d37096d"),
                new ProductDTO(9L,"Monopoly","juego de mesa",
                        new CategoryDTO(0L,"familiar"),
                        new PlayerDTO(0L, "2 a 4 jugadores")),
                true,
                null
        );
        */

        Listing listing;
        try{
            listingValidator.validateListing(listingDTO);
            listing = dtoConverter.convertToListing(listingDTO);

        }
        catch(ListingValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        listingService.createOrUpdateListing(listing);
        return new ResponseEntity<>("Publicacion creada correctamente", HttpStatus.CREATED);
    }
    //Redundante ya que create-listing tambien sirve para actualizar
    @PostMapping("update-listing")
    public ResponseEntity<String> updateListing(@RequestBody ListingDTO listingDTO) {
        try{

            ListingDTO listingDTO1 = new ListingDTO(1L,"nuevo titulo de venta random 3","nueva descripcion de la venta random2",39,999.99,null,
                    new ProductDTO(1L,"Clue", "", new CategoryDTO(0L,"familiar"), new PlayerDTO(0L, "2 a 4 jugadores")),
                    true,
                    Arrays.asList(
                            new ImageDTO("url3", 1L),
                            new ImageDTO("url5", 1L),
                            new ImageDTO("url6",1L)
            ));
            listingValidator.validateListing(listingDTO1);
            Listing listing = dtoConverter.convertToListing(listingDTO1);
            listingService.createOrUpdateListing(listing);

            return new ResponseEntity<>("Publicacion creada correctamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //DELETE
    @DeleteMapping("/delete-listing")
    public ResponseEntity<String> deleteListing(@RequestParam long listingId) {
        try{
            listingService.deleteListing(listingId);
            return new ResponseEntity<>("Publicacion eliminada correctamente", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
