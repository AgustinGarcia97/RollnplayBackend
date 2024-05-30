package com.example.serverapi.controller;

import com.example.serverapi.database.service.SaleService;
import com.example.serverapi.exceptions.UserNotFoundException;
import com.example.serverapi.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/create-sale")
    public ResponseEntity<?> createSale (@RequestParam Integer id) throws UserNotFoundException {
        try {
            saleService.createSale(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
