package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.BrandDTO;
import com.example.serverapi.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandConverter {
    public Brand convertToEntity(BrandDTO brandDTO){
        Brand brand = new Brand();
        if(brandDTO.getBrandId() != null) {
            brand.setBrandId(brandDTO.getBrandId());
        }
        brand.setBrandName(brandDTO.getBrandName());
        return brand;
    }

    public BrandDTO convertToDto(Brand brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setBrandId(brand.getBrandId());
        brandDTO.setBrandName(brand.getBrandName());
        return brandDTO;
    }

}
