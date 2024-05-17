package com.example.serverapi.database.HibernateAPI.dao.product_dao;

import com.example.serverapi.model.Product;

import java.util.Map;

public interface ProductDAO {
    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProduct(Long id, Map<String,Object> fieldUpdates);
    void deleteProduct(Long id);

}
