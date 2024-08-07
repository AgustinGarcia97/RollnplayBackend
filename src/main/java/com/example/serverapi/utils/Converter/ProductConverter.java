package com.example.serverapi.utils.Converter;

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
            if(productDTO.getProductName() != null)
                product.setProductName(productDTO.getProductName());
            if(productDTO.getProductDescription() != null)
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
            productDTO.setBrandName(product.getProductBrand().getBrandName());
            productDTO.setDifficulty(product.getDifficulty().getDifficultyName());

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
