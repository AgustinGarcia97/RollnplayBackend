package com.example.serverapi.controller;


import com.example.serverapi.database.service.ProductService;
import com.example.serverapi.exceptions.ProductValidationException;
import com.example.serverapi.model.Category;
import com.example.serverapi.model.Player;
import com.example.serverapi.model.Product;
import com.example.serverapi.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductValidator productValidator;

    @PostMapping("/create-product")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        try{
            this.productValidator.validateProduct(product);

        }
        catch(ProductValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        this.productService.createProduct(product);
        return new ResponseEntity<>("Producto creado correctamente", HttpStatus.CREATED);



    }


}
