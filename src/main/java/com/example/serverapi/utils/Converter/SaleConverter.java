package com.example.serverapi.utils.converter;

import com.example.serverapi.database.service.UserService;
import com.example.serverapi.dto.SaleDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.exceptions.userExceptions.UserNotFoundException;
import com.example.serverapi.model.Sale;
import com.example.serverapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaleConverter {

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ListingConverter listingConverter;

    @Autowired
    private UserService userService;

    public Sale convertToEntity(SaleDTO saleDTO){
        Sale sale = new Sale();
        try {
            if (saleDTO.getSaleId() != null) {
                sale.setSaleId(saleDTO.getSaleId());
            }

            sale.setSaleDate(saleDTO.getSaleDate());
            Optional<User> existence = userService.getUserById(saleDTO.getUserId());

            if (existence.isPresent()) {
                sale.setUser(existence.get());
            } else {
                throw new UserNotFoundException("User with ID" + saleDTO.getUserId() + "not found");
            }

            try {
                sale.setListingsSales(listingConverter.convertToEntity(saleDTO.getListingDTO()));
            } catch (Exception e) {
                throw new ConversionException("Error converting ListingDTO to listing ", e);
            }

            try {
                sale.setProductSales(productConverter.convertToEntity(saleDTO.getProductDT0()));
            } catch (Exception e) {
                throw new ConversionException("Error converting ProductDTO to product ", e);
            }
        }
        catch (Exception e) {
            throw e;
        }

        return sale;
    }
}
