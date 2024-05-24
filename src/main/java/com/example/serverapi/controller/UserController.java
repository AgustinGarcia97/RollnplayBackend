package com.example.serverapi.controller;
import com.example.serverapi.database.service.UserService;
import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.utils.DTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.serverapi.model.User;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private DTOConverter dtoConverter;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;

    }




    @GetMapping("/getUser")
    public Optional<User> getUsers(){
        return this.userService.getUserById(UUID.randomUUID());

    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){

        try{
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setName("new name");
            userDTO1.setUsername("new username");
            userDTO1.setPassword("password");
            userDTO1.setMail("mail");
            userDTO1.setSeller(true);
            userDTO1.setAdress("some street");
            userDTO1.setDocument("13132124");
            userDTO1.setPhoneNumber("113333444");
            userDTO1.setUserId(UUID.fromString("1bec0412-ab44-4135-8d7f-c70eb107c1b1"));
            User user = dtoConverter.convertToUser(userDTO1);

            userService.createOrUpdateUser(user);
            return ResponseEntity.ok(user);
        }
        catch(Exception e){
            logger.error(e.getMessage());

        }
        return ResponseEntity.badRequest().build();

    }






}
