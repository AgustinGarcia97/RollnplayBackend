package com.example.serverapi.database.service;


import com.example.serverapi.database.repository.UserRepository;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void createOrUpdateUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<Listing> getListingById(int id){
       return userRepository.findById(id).get().getListings();
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
