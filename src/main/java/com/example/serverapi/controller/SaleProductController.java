package com.example.serverapi.controller;

import com.example.serverapi.database.service.SaleProductService;
import com.example.serverapi.database.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleProductController {
    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private SaleService saleService;

    @PostMapping("/load-product-sale")
    public void loadProduct(@RequestParam Integer sale_id, @RequestParam Long product_id, @RequestParam Integer quantity, @RequestParam Long listing_id) {
        saleProductService.loadProducts(sale_id, product_id, quantity);
    }

    @PutMapping("/update-quantity-product")
    public ResponseEntity<?> updateQuantityProduct(@RequestParam Integer sale_id, @RequestParam Long product_id, @RequestParam Integer quantity) {
        try {
            saleProductService.updateProduct(sale_id, product_id, quantity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-product-sale")
    public ResponseEntity<?> deleteProductSale(@RequestParam Integer sale_id, @RequestParam Long product_id) {
        try {
            saleProductService.noQuantity(sale_id, product_id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
