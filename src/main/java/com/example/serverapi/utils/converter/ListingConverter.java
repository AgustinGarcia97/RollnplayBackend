package com.example.serverapi.utils.converter;

import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.model.Listing;
import org.springframework.stereotype.Component;

@Component
public class ListingConverter {

    public Listing convertToEntity(ListingDTO listingDTO){
        Listing listing = new Listing();
        try{
            if(listingDTO.getListingId() != null){
                listing.setListingId(listingDTO.getListingId());
                }

            listing.setTitle(listingDTO.getTitle());
            listing.setDescription(listingDTO.getDescription());
            listing.setPrice(listingDTO.getPrice());
            listing.setStock(listingDTO.getStock());

        } catch(IllegalArgumentException e) {
            System.out.println("values cannot be null:"+e.getMessage());
        } catch(ConversionException e) {
            System.out.println("conversor error:"+e.getMessage());
        } catch(Exception e) {
            System.out.println("error:"+e.getMessage());

        }return listing;
    }

    public ListingDTO convertToDTO(Listing listing){
        ListingDTO listingDTO = new ListingDTO();
        try{
            listingDTO.setListingId(listing.getListingId());
            listingDTO.setTitle(listing.getTitle());
            listingDTO.setDescription(listing.getDescription());
            listingDTO.setPrice(listing.getPrice());
            listingDTO.setStock(listing.getStock());
            listingDTO.setUserId(listing.getUser().getUserId());

        } catch(IllegalArgumentException e) {
            System.out.println("values cannot be null:"+e.getMessage());
        } catch(ConversionException e) {
            System.out.println("conversor error:"+e.getMessage());
        } catch(Exception e) {
            System.out.println("error:"+e.getMessage());

        }return listingDTO;

    }




    }

