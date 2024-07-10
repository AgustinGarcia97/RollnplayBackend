package com.example.serverapi.controller;


import com.example.serverapi.database.service.SaleService;
import com.example.serverapi.dto.SaleDTO;
import com.example.serverapi.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @PostMapping("/create-sale")
    public ResponseEntity<?> createSale(@RequestBody SaleDTO saleDTO){
        Sale sale = null;
        try{
            sale = saleService.createSale(saleDTO);
            if(sale==null){
                return ResponseEntity.badRequest().body("Error de stock");
            }
            return ResponseEntity.ok(sale);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-sales/user")
    public ResponseEntity<?> getSalesUser(@RequestParam UUID userId){
        List<SaleDTO> saleDTO = null;
        try{
            saleDTO = saleService.getAllSalesDTOByUserId(userId);
            return ResponseEntity.ok(saleDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-purchases")
    public ResponseEntity<?> getPurchasesUser(@RequestParam UUID userId){
        List<SaleDTO> saleDTO = null;
        try{
            saleDTO = saleService.getAllPurchasesDTOByUserId(userId);
            return ResponseEntity.ok(saleDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }






}
