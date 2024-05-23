package com.example.serverapi.validator;

import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.exceptions.ListingValidationException;
import com.example.serverapi.model.Listing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListingValidator {


    public void validateListing(ListingDTO listing) {
        List<String> missingValues = new ArrayList<String>();

        if(listing.getTitle() == null){
            missingValues.add("Titulo de la publicacion");
        }
        if(listing.getDescription() == null){
            missingValues.add("Descripcion de la publicacion");
        }
        if(listing.getProductDTO() == null) {
            missingValues.add("Producto de la publicacion");
        }

        if(missingValues.size() > 0) {
            throw new ListingValidationException("Faltan los siguientes campos: "+missingValues);
        }
    }

}
