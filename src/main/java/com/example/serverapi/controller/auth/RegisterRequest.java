package com.example.serverapi.controller.auth;


import com.example.serverapi.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String document;
    private String username;
    private String phoneNumber;
    private Role role;
}
