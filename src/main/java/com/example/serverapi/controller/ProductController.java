package com.example.serverapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverapi.database.service.ProductService;
import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.exceptions.userExceptions.UserConversionException;
import com.example.serverapi.model.Product;

@RestController
@RequestMapping("productos")
public class ProductController {

  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<Page<Product>> getProducts(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {
    if (page == null || size == null)
      return ResponseEntity.ok(productService.getProducts(PageRequest.of(0, Integer.MAX_VALUE)));
    return ResponseEntity.ok(productService.getProducts(PageRequest.of(page, size)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
    try {
      return ResponseEntity.ok(productService.findById(id));
      // Optional<ProductDTO> productDTO = productService.findByIdDTO(id);
      // if (productDTO.isPresent()) {
      // return new ResponseEntity<>(productDTO.get(), HttpStatus.OK);
      // } else {
      // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      // }

    } catch (UserConversionException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
    try {
      productService.createOrUpdateProduct(productDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();

    } catch (UserConversionException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

    }
  }

  @PutMapping("/update-product")
  public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
    try {
      productService.createOrUpdateProduct(productDTO);
      return ResponseEntity.status(HttpStatus.OK).build();

    } catch (UserConversionException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

    }
  }

  @DeleteMapping("/delete-product")
  public ResponseEntity<?> deleteProduct(@RequestParam Long productId) {
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
