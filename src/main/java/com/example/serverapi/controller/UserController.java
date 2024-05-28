package com.example.serverapi.controller;
import com.example.serverapi.database.service.UserService;
import com.example.serverapi.dto.UserDTO;
import com.example.serverapi.utils.DTOConverter;
import org.aspectj.apache.bcel.generic.RET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> getUsers(){

        try{
            Optional<User> user = this.userService.getUserById(UUID.fromString("4ac1ef3b-8ce5-4675-9c12-8d583d37096d"));
            if(user.isPresent()){
                UserDTO userDTO = dtoConverter.convertToUserDTO(user.get());
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){

        try{
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setName("new name 3");
            userDTO1.setUsername("new username 3");
            userDTO1.setPassword("password 3");
            userDTO1.setMail("mail 3");
            userDTO1.setSeller(true);
            userDTO1.setAdress("some street 3");
            userDTO1.setDocument("13132124 3");
            userDTO1.setPhoneNumber("113333444 3");
            //userDTO1.setUserId(UUID.fromString("05b3a213-2523-4c83-a36c-3d3db8b5274a"));
            User user = dtoConverter.convertToUser(userDTO1);

            userService.createOrUpdateUser(user);
            return ResponseEntity.ok(user);
        }
        catch(Exception e){
            logger.error(e.getMessage());

        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDTO){
        try{
            userService.deleteUser(UUID.fromString("a5170f50-f575-4725-82dd-20837a7265e3"));
            return ResponseEntity.ok("Usuario eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }






}
