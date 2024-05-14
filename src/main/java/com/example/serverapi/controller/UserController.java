package com.example.serverapi.controller;
import com.example.serverapi.database.dao.user_dao.UserDAOImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.serverapi.model.User;
@RestController
public class UserController {
    private final UserDAOImplement userDAO;

    @Autowired
    public UserController(UserDAOImplement userDAO){
        this.userDAO = userDAO;
    }

    @GetMapping("/profile")
    public User getUser(){
        User u = userDAO.getUserById("1b80b41c-97dd-47f8-bbbf-199f2a1e193a");

        return u;
    }


}
