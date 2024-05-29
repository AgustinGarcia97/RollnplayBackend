package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.SaleRepository;
import com.example.serverapi.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;


    public void saveSale(Sale sale){
        saleRepository.save(sale);
    }


}
