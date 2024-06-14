package com.example.serverapi.utils.converter;

import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product convertToEntity(ProductDTO productDTO){
        Product product = new Product();
        try{
            if(productDTO.getProductId() != null){
                product.setProductId(productDTO.getProductId());
            }
            product.setProductName(productDTO.getProductName());
            product.setProductDescription(productDTO.getProductDescription());

        }
        catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return product;
    }

    public ProductDTO convertToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        try{
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductDescription(product.getProductDescription());
            productDTO.setCategoryName(product.getCategory().getCategoryName());
            productDTO.setPlayerCounter(product.getPlayers().getNumberOfPlayers());

        }
        catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return productDTO;
    }
}
