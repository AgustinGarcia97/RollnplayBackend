package com.example.serverapi.database.dao.product_dao;

import com.example.serverapi.model.Product;

import java.util.Map;

public interface ProductDAO {
    Product createProduct(Product product);
    Product getProductById(String id);
    Product updateProduct(String id, Map<String,Object> fieldUpdates);
    void deleteProduct(String id);

}
