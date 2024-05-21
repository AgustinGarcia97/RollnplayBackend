package com.example.serverapi.validator;

import com.example.serverapi.database.service.ProductService;
import com.example.serverapi.exceptions.ProductValidationException;
import com.example.serverapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    private ProductService productService;

    @Autowired
    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    public void validateProduct(Product product) {
        List<String> missingValues = new ArrayList<String>();
        if(product.getProductName() == null){
            missingValues.add("Nombre del producto");
        }
        else if(product.getProductDescription() == null){
            missingValues.add("Descripcion del producto");
        }
        else if(product.getCategory() == null){
            missingValues.add("Categoria del producto");
        }
        else if(product.getPlayers() == null){
            missingValues.add("Jugadores del producto");
        }

        if(missingValues.size() > 0){
            throw new ProductValidationException("Faltan los siguentes campos :"+missingValues.toString());
        }
    }
}
