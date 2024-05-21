package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.ProductRepository;
import com.example.serverapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product findByName(String name) {
        return productRepository.findByProductName(name);
    }


    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }


    public Product createProduct(Product product) {
        Product p = null;
        if (!existsProduct(product.getProductId())){
            p = productRepository.save(product);
        }
        return p;

    }


    public Product updateProduct(Product product) {
        return null;
    }


    public void deleteProduct(long id) {
            productRepository.deleteById(id);
    }


    public Boolean existsProduct(long id) {
        return findById(id) != null;
    }


}
