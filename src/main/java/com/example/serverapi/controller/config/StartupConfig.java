package com.example.serverapi.controller.config;

import com.example.serverapi.database.repository.UserRepository;
import com.example.serverapi.model.Role;
import com.example.serverapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StartupConfig {

    private final UserRepository userRepository;

    @Autowired
    public StartupConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    CommandLineRunner initDatabase( ) {

        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String adminEmail = "admin@mail.com";


            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User user = User.builder()
                        .firstName("Admin")
                        .lastName("Admin")
                        .password(passwordEncoder.encode("123456"))
                        .email(adminEmail)
                        .address("123 Main St")
                        .phoneNumber("1234567890")
                        .document("123456789")
                        .isSeller(false)
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(user);
            }
        };
    }
}
