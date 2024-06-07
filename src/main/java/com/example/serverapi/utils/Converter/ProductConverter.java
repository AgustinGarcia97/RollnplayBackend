package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    @Autowired
    CategoryConverter categoryConverter;
    @Autowired
    PlayerConverter playerConverter;


    public Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        if(productDTO.getProductId() != null){
            product.setProductId(productDTO.getProductId());
        }
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setCategory(
                categoryConverter.convertToEntity(productDTO.getProductCategory())
        );
        product.setPlayers(
                playerConverter.convertToEntity(productDTO.getProductPlayers())
        );


        return product;
    }
}
