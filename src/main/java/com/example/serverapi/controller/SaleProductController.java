package com.example.serverapi.controller;

import com.example.serverapi.database.repository.SaleListingRepository;
import com.example.serverapi.database.service.SaleListingService;
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
    private SaleListingService saleListingService;

    @Autowired
    private SaleListingRepository saleListingRepository;

    @PostMapping("/load-product-sale")
    public ResponseEntity<?> loadProduct(@RequestParam Integer sale_id, @RequestParam Long product_id, @RequestParam Integer quantity, @RequestParam Long listing_id) {
        try {
            Integer matchListingProduct = saleListingService.countListingProduct(listing_id, product_id);
            if (matchListingProduct > 0) {
                try {
                    saleListingRepository.sp_product_and_listing(sale_id, product_id, quantity, listing_id);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
