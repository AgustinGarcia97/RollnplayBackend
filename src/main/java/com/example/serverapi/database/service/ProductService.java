package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.CategoryRepository;
import com.example.serverapi.database.repository.PlayerRepository;
import com.example.serverapi.database.repository.ProductRepository;
import com.example.serverapi.model.Category;
import com.example.serverapi.model.Player;
import com.example.serverapi.model.Product;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product findByName(String name) {
        return productRepository.findByProductName(name);
    }


    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }


    public Product createProduct(Product product) {
        Product p = null;

        //Busca la si el registro numberOfPlayers si existe
        String numberOfPlayers = product.getPlayers().getNumberOfPlayers();
        Player existingPlayer = playerRepository.findByNumberOfPlayers(numberOfPlayers);
        //Si la cantDeJugadores ya existe en la tabla Players, no la crea, sino que agrega la que ya existe
        if (existingPlayer != null) {
            product.setPlayers(existingPlayer);
        }

        //Busca la categoria si existe
        String categoryName = product.getCategory().getCategoryName();
        Category categoryExisting = categoryRepository.findFirstByCategoryName(categoryName);
        //Si la categoria ya existe en la tabla Category, no la crea, sino que agrega la que ya existe
        if(categoryExisting != null) {
            product.setCategory(categoryExisting);
        }

        p = productRepository.save(product);
        return p;
    }


    public Product updateProduct(Product product) {
        Optional<Product> old = productRepository.findById(product.getProductId())
        if (old.isPresent()) {
            Product p = old.get();
            p.setProductName(product.getProductName());
            p.setProductDescription(product.getProductDescription());
            p.setCategory(product.getCategory());
            p.setPlayers(product.getPlayers());
            return productRepository.save(p);
        }
        return null;
    }


    public void deleteProduct(long id) {
            productRepository.deleteById(id);
    }


    public Boolean existsProduct(long id) {
        return findById(id) != null;
    }

    public Product getProductByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }




}
