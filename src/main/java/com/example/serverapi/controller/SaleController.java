package com.example.serverapi.controller;


import com.example.serverapi.database.service.SaleService;
import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.dto.SaleDTO;
import com.example.serverapi.model.Sale;
import com.example.serverapi.utils.DTOConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ListingController listingController;

    @Autowired
    private DTOConverter dtoConverter;




    //TESTEAR
    @PostMapping("/buy-items")
    public ResponseEntity<String> buyItems(@RequestBody Map<String, Object> request) throws Exception {

        try{
            SaleDTO saleDTO = new ObjectMapper().convertValue(request.get("saleDTO"), SaleDTO.class);
            ListingDTO listingDTO = new ObjectMapper().convertValue(request.get("listingDTO"), ListingDTO.class);
            Sale sale = dtoConverter.convertToSale(saleDTO, listingDTO);
            if(sale != null){
                listingDTO.setStock(listingDTO.getStock() - saleDTO.getProductsQuantity()); // actualizo stock
                listingController.createListing(listingDTO);// actualizo stock
                saleService.saveSale(sale);
                return new ResponseEntity<>("Venta realizada correctamente", HttpStatus.OK);

            }

            return new ResponseEntity<>("Error al finalizar la venta", HttpStatus.BAD_REQUEST);

        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
