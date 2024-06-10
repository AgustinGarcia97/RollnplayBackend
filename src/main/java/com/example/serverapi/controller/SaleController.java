package com.example.serverapi.controller;


import com.example.serverapi.database.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleController {

    private final SaleService saleSerivce;

    @Autowired
    public SaleController(SaleService saleSerivce){
        this.saleSerivce = saleSerivce;
    }








}
