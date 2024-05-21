package com.example.serverapi.controller;


import com.example.serverapi.database.service.ProductService;
import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.exceptions.ProductValidationException;
import com.example.serverapi.model.Category;
import com.example.serverapi.model.Player;
import com.example.serverapi.model.Product;
import com.example.serverapi.utils.DTOConverter;
import com.example.serverapi.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private DTOConverter dtoConverter;


    @GetMapping("/find-product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable long id) {
        try{
            Optional<Product> product = productService.findById(id);
            if(product.isPresent()) {
                System.out.println("hola");
                ProductDTO productDTO = new ProductDTO(
                        product.get().getProductId(),
                        product.get().getProductName(),
                        product.get().getProductDescription(),
                        dtoConverter.convertToCategoryDTO(product.get().getCategory()),
                        dtoConverter.convertToPlayersDTO(product.get().getPlayers())
                        );
                return new ResponseEntity<>(productDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("NOOOOO",HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

    }


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
