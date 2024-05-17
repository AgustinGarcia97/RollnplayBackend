package com.example.serverapi.dto;

import com.example.serverapi.model.Listing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String UUID;
    private String name;
    private String password;
    private String mail;
    private List<Listing> listings;
}
