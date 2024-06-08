package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.model.Role;
import com.example.serverapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class UserConverter {


    private ListingConverter listingConverter;


    private PurchaseConverter purchaseConverter;


    private SaleConverter saleConverter;



    public User convertToEntity(UserDTO userDTO){
        User user = new User();
        if(userDTO.getUserId() != null){
            user.setUserId(userDTO.getUserId());
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getMail());
        user.setName(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDocument(userDTO.getDocument());
        user.setRole(Role.valueOf(userDTO.getRole()));

        if(userDTO.getListingsDTO() != null){
            user.setListings(userDTO
                            .getListingsDTO()
                            .stream()
                            .map(listingDTO -> listingConverter.convertToEntity(listingDTO))
                            .collect(Collectors.toList())
            );
        }

        if(userDTO.getPurchasesDTO() != null) {
            user.setPurchases(userDTO
                    .getPurchasesDTO()
                    .stream()
                    .map(purchaseDTO -> purchaseConverter.convertToEntity(purchaseDTO))
                    .collect(Collectors.toList()));
        }

        if(userDTO.getSalesDTO() != null){
            user.setSales(userDTO.getSalesDTO()
                    .stream()
                    .map(saleDTO -> saleConverter.convertToEntity(saleDTO))
                    .collect(Collectors.toList()));
        }

        return user;
    }
}
