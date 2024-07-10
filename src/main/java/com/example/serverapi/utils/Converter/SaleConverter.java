package com.example.serverapi.utils.Converter;

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

/*
    public Sale convertToEntity(SaleDTO saleDTO){
        Sale sale = new Sale();
        try {
            if (saleDTO.getSaleId() != null) {
                sale.setSaleId(saleDTO.getSaleId());
            }

            sale.setSaleDate(saleDTO.getSaleDate());
            Optional<User> existence = userService.getUserById(saleDTO.getUserId());

            /*
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


        }
        catch (Exception e) {
            throw e;
        }

        return sale;
    } */
    public SaleDTO convertToSaleDTO(Sale sale){
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(sale.getSaleId());
        saleDTO.setSaleDate(sale.getSaleDate());
        saleDTO.setPrice(sale.getPrice());
        saleDTO.setBuyerName(sale.getBuyer().getUsername());
        saleDTO.setSellerName(sale.getSeller().getFirst().getUsername());
        return saleDTO;

    }
}
