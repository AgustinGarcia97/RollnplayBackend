package com.example.serverapi.utils.Converter;

import com.example.serverapi.database.service.UserService;
import com.example.serverapi.dto.PurchaseDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.exceptions.userExceptions.UserNotFoundException;
import com.example.serverapi.model.Purchase;
import com.example.serverapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.serverapi.utils.Converter.ListingConverter;

import java.util.Optional;

@Component
public class PurchaseConverter {

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ListingConverter listingConverter;

    @Autowired
    private UserService userService;

    public Purchase convertToEntity(PurchaseDTO purchaseDTO) {
        Purchase purchase = new Purchase();
        try {

            if (purchaseDTO.getPurchaseId() != null) {
                purchase.setPurchaseId(purchaseDTO.getPurchaseId());
            }

            purchase.setPurchaseDateTime(purchaseDTO.getPurchaseDateTime());


            try {
                purchase.setListingToList(listingConverter.convertToEntity(purchaseDTO.getListingDTO()));
            }
            catch (Exception e) {
                throw new ConversionException("Error converting ListingDTO to listing", e);
            }

            try {
                purchase.setProductToList(productConverter.convertToEntity(purchaseDTO.getProductDTO()));
            }
            catch (Exception e) {
                throw new ConversionException("Error converting ProductDTO to product", e);
            }


        }
        catch(UserNotFoundException | ConversionException e) {
            System.err.println(e.getMessage());
            throw e;
        }

        return purchase;
    }

}
