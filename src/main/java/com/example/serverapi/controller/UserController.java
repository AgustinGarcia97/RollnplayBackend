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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String userId){

        try{
            Optional<User> user = this.userService.getUserById(UUID.fromString(userId));
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

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        try{
            List<UserDTO> usersDTOList = userService
                    .findAll()
                    .stream()
                    .map(user -> dtoConverter.convertToUserDTO(user))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(usersDTOList, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){

        try{

            User user = dtoConverter.convertToUser(userDTO);

            userService.createOrUpdateUser(user);
            return ResponseEntity.ok(user);
        }
        catch(Exception e){
            logger.error(e.getMessage());

        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestParam String userId){
        try{
            userService.deleteUser(UUID.fromString(userId));
            return ResponseEntity.ok("Usuario eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }






}
