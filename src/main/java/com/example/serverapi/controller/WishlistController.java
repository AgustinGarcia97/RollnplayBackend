package com.example.serverapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverapi.database.repository.UserRepository;
import com.example.serverapi.database.service.WishlistService;
import com.example.serverapi.model.Product;
import com.example.serverapi.model.User;

@RestController
@RequestMapping("wishlist")
public class WishlistController {
  @Autowired
  private WishlistService wishlistService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public Page<Product> getProducts(@RequestParam String email) {
    System.out.println("Getting products, email: " + email);
    User user = userRepository.findByEmail(email).get();
    System.out.println("Getting products, userId: " + user.getUserId());
    return wishlistService.getProducts(user.getUserId());
  }

  @PostMapping
  public void toggleProduct(
      @RequestParam String email,
      @RequestParam Long productId,
      @RequestParam boolean remove) {
    User user = userRepository.findByEmail(email).get();
    UUID userId = user.getUserId();
    if (remove) {
      wishlistService.removeProduct(userId, productId);
    } else {
      wishlistService.addProduct(userId, productId);
    }
  }
}
