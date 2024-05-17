package com.example.serverapi.utils;

import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.User;

import java.util.stream.Collectors;

public class DTOConverter {
    public UserDTO convertToUSERdto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setMail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setListingsDTO(user.getListings()
                .stream()
                .map(this::converToListingDTO)
                .collect(Collectors.toList()));
        return userDTO;


    }

    private ListingDTO converToListingDTO(Listing listing) {
        ListingDTO listingDTO = new ListingDTO();

        return null;
    }
}
