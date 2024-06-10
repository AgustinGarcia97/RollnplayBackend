package com.example.serverapi.utils.converter;

import com.example.serverapi.dto.CategoryDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        try{
            if(categoryDTO.getCategoryId() != null) {
                category.setCategoryId(categoryDTO.getCategoryId());
            }
            category.setCategoryName(categoryDTO.getCategoryName());


        }catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return category;
    }

    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        try{
            categoryDTO.setCategoryId(category.getCategoryId());
            categoryDTO.setCategoryName(category.getCategoryName());

        }catch(IllegalArgumentException e){
            System.out.println("values cannot be null:"+e.getMessage());
        }
        catch(ConversionException e){
            System.out.println("conversor error:"+e.getMessage());

        }
        catch(Exception e){
            System.out.println("error:"+e.getMessage());
        }
        return categoryDTO;
    }

}
