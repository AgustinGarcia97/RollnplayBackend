package com.example.serverapi.controller;


import com.example.serverapi.database.service.ProductService;
import com.example.serverapi.dto.CategoryDTO;
import com.example.serverapi.dto.PlayerDTO;
import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.exceptions.ProductValidationException;
import com.example.serverapi.exceptions.userExceptions.UserConversionException;
import com.example.serverapi.exceptions.userExceptions.UserNotFoundException;
import com.example.serverapi.model.Category;
import com.example.serverapi.model.Player;
import com.example.serverapi.model.Product;
import com.example.serverapi.utils.DTOConverter;
import com.example.serverapi.validator.ProductValidator;
import jakarta.transaction.Transactional;
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


    private ProductService productService;
    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-product/id")
    public ResponseEntity<?> getProductById(@RequestParam Long id) {
        try{
            Optional<ProductDTO> productDTO = productService.findByIdDTO(id);
            if(productDTO.isPresent()) {
                return new ResponseEntity<>(productDTO.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch(UserConversionException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }  catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try{
            productService.createOrUpdateProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch(UserConversionException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @PutMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        try{
            productService.createOrUpdateProduct(productDTO);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch(UserConversionException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<?> deleteProduct(@RequestBody Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
        } catch (UserConversionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
