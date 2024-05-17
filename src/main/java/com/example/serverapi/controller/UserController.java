package com.example.serverapi.controller;
import com.example.serverapi.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.serverapi.model.User;

@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;

    }




    @GetMapping("/getUser")
    public User getUsers(){
        return this.userService.getUserById("7c72d9f4-3bc0-4b21-9216-65f3ad2dde2b");

    }




}
