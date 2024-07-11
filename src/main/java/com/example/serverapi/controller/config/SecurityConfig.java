package com.example.serverapi.controller.config;



import com.example.serverapi.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;


import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


/*
Esta clase configura cual sera la seguridad HTTP. Es una cadena de responsabilidad de seguridad

Se podra configurar una cadena de responsabilidades sobre seguridad. Gracias a esto se puede hacer
de configuraciones.

*/


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers("/user/**").hasAnyAuthority(Role.USER.name())
                        .requestMatchers("/user/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/listing/**").hasAnyAuthority(Role.USER.name())
                        .requestMatchers("/listing/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/listing/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/delete-product").hasAuthority(Role.ADMIN.name())
                        .anyRequest()
                        .authenticated())

                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}

//aca se verifica, que endpoints de la app, tendrian que pedir autorizacion, y cuales no. Y ademas que endpoints voy a querer que solo accedan aquellos request que pertenezcan a usuario
//y rol en particular

//Todos los put y post de producto tendrian que tener un rol de ADMIN


//para ver a nivel codigo que el token no fue manipulado ver clase JwtAuthenticationFilter