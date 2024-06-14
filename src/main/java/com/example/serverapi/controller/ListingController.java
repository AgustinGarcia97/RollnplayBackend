package com.example.serverapi.controller;

import com.example.serverapi.database.service.ListingService;
import com.example.serverapi.dto.*;
import com.example.serverapi.exceptions.ListingValidationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class ListingController {

    private ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;

    }

    @GetMapping("/get-listing/id")
    public ResponseEntity<?> getListing(@RequestParam Long id) {
        try{
            return ResponseEntity.ok(listingService.getListing(id));
        }
        catch(ListingValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-listings")
    public ResponseEntity<?> getListings() {
        try{
            List<ListingDTO> listingDTOS = new ArrayList<ListingDTO>();
            Optional<List<ListingDTO>> existences = listingService.getAllListingsDTO();
            if(existences.isPresent()){
                listingDTOS = existences.get();
            }
            return ResponseEntity.ok(listingDTOS);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/create-listing")
    public ResponseEntity<?>  createListing(@RequestBody ListingDTO listingDTO){
        try{
            listingService.createOrUpdateListing(listingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Publicacion creada correctamente");
        } catch(PersistenceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update-listing")
    public ResponseEntity<?> updateListing(@RequestBody ListingDTO listingDTO) {
        try{
            listingService.createOrUpdateListing(listingDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Listing updated successfully");
        }
        catch(PersistenceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-listing")
    public ResponseEntity<?> deleteListing(@RequestParam Long id) {
        try{
            listingService.deleteListing(id);
            return ResponseEntity.status(HttpStatus.OK).body("Listing deleted successfully");
        }
        catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }








}
