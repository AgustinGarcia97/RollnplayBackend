package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.SaleProductRepository;
import com.example.serverapi.model.SaleProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleProductService {
    @Autowired
    private SaleProductRepository saleProductRepository;

    public void loadProducts(Integer sale_id, Long product_id, Integer quantity) {
        SaleProduct saleProduct = new SaleProduct();
        saleProduct.setSaleId(sale_id);
        saleProduct.setProductId(product_id);
        saleProduct.setQuantity(quantity);
        System.out.println("sale product: " + saleProduct.toString());

        saleProductRepository.save(saleProduct);
    }

}
