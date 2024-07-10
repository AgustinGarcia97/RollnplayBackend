package com.example.serverapi.database.service.securityservice;


import com.example.serverapi.controller.auth.AuthenticationRequest;
import com.example.serverapi.controller.auth.AuthenticationResponse;
import com.example.serverapi.controller.auth.RegisterRequest;
import com.example.serverapi.controller.config.JwtService;
import com.example.serverapi.database.repository.UserRepository;
import com.example.serverapi.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())

                .document(request.getDocument())
                .role(request.getRole())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken( user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
      //autentica
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())); //si esta linea pasa, significa que el usuario esta autenticado dentro del server sin hacer una query de checkeo a la db
        //como se que existe, se busca la info del usuario

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        //si el usuario esta autenticado y encontre la data retorno un token
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .userId(user.getUserId())
                .role(user.getRole())
                .build();
    }
}
