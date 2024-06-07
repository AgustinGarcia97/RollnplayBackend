package com.example.serverapi.controller;


import com.example.serverapi.database.service.ProductService;
import com.example.serverapi.dto.CategoryDTO;
import com.example.serverapi.dto.PlayerDTO;
import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.exceptions.ProductValidationException;
import com.example.serverapi.model.Category;
import com.example.serverapi.model.Player;
import com.example.serverapi.model.Product;
import com.example.serverapi.utils.DTOConverter;
import com.example.serverapi.validator.ProductValidator;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                ProductDTO productDTO = new ProductDTO(
                        product.get().getProductId(),
                        product.get().getProductName(),
                        product.get().getProductDescription(),
                        dtoConverter.convertToCategoryDTO(product.get().getCategory()),
                        dtoConverter.convertToPlayersDTO(product.get().getPlayers()),
                        product.get().getProductPrice()
                        );
                return new ResponseEntity<>(productDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Producto no encontrado",HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

    }

    @GetMapping("/get-all-products")
    public ResponseEntity<?> getAllProducts() {
        try{
            List<ProductDTO> productsDTO = productService.getAllProducts()
                    .stream()
                    .map(product -> dtoConverter.convertToProductDTO(product))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productsDTO, HttpStatus.OK);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping("/create-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        try{
            System.out.println(productDTO);
            this.productService.createOrUpdateProduct(productDTO);
        }
        catch(ProductValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Producto creado correctamente", HttpStatus.CREATED);

    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam long productId){
        try{
            productService.deleteProduct(productId);
            return new ResponseEntity<>("Producto eliminado correctamente",HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Producto no encontrado, o Id invalido",HttpStatus.BAD_REQUEST);

        }

    }




}
