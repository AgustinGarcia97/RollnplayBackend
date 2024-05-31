package com.example.serverapi.controller;

import com.example.serverapi.database.service.SaleProductService;
import com.example.serverapi.database.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
