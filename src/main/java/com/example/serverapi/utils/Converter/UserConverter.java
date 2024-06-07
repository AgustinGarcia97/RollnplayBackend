package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.model.Role;
import com.example.serverapi.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {

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

        user.setListings();
        user.setPurchases();
        user.setSales();

        return user;
    }
}
