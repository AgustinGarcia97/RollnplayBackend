package com.example.serverapi.database.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.example.serverapi.database.repository.UserRepository;
import com.example.serverapi.database.repository.WishlistRepository;
import com.example.serverapi.model.Product;
import com.example.serverapi.model.User;
import com.example.serverapi.model.Wishlist;

@Service

public class WishlistService {
  @Autowired
  private WishlistRepository wishlistRepository;

  @Autowired
  private ProductService productService;

  @Autowired
  private UserRepository userRepository;

  public Page<Product> getProducts(UUID userId) {
    Set<Product> products = getWishlist(userId).getProducts();
    List<Product> productList = List.copyOf(products);
    return new PageImpl<Product>(productList);
  }

  // find by user id
  public Wishlist getWishlist(UUID userId) {
    User user = userRepository.findById(userId).get();
    Wishlist wishlist = user.getWishlist();
    System.out.println(wishlist);
    if (wishlist == null) {
      wishlist = new Wishlist();
      user.setWishlist(wishlist);
      userRepository.save(user);
      wishlist.setUser(user);
      wishlistRepository.save(wishlist);
      return wishlist;
    } else {
      return user.getWishlist();
    }
  }

  public void addProduct(
      UUID userUuid,
      Long productId) {
    Wishlist wishlist = getWishlist(userUuid);
    Product product = productService.findById(productId).get();
    wishlist.addProduct(product);
    wishlistRepository.save(wishlist);
  }

  public void removeProduct(
      UUID userUuid,
      Long productId) {
    Wishlist wishlist = getWishlist(userUuid);
    Product product = productService.findById(productId).get();
    wishlist.removeProduct(product);
    wishlistRepository.save(wishlist);
  }
}
