package com.example.serverapi.database.service;


import com.example.serverapi.database.repository.BrandRepository;
import com.example.serverapi.dto.BrandDTO;
import com.example.serverapi.model.Brand;
import com.example.serverapi.utils.Converter.DtoAssembler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private BrandRepository brandRepository;
    private DtoAssembler dtoAssembler;

    @Autowired
    public BrandService(BrandRepository brandRepository, DtoAssembler dtoAssembler) {
        this.brandRepository = brandRepository;
        this.dtoAssembler = dtoAssembler;
    }

    @Transactional
    public Brand createOrUpdateBrand(BrandDTO brandDTO) {
        Brand brand = null;
        try{
            brand = dtoAssembler.getBrandEntity(brandDTO);
            brand = brandRepository.save(brand);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return brand;
    }

    public Brand getBrandById(Long id) {
        Brand brand = null;
        try{
            Optional<Brand> existence = brandRepository.findById(id);
            if(existence.isPresent()){
                brand = existence.get();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return brand;
    }

    public List<BrandDTO> getAllBrandsDTO(){
        try{
            return brandRepository.findAll().stream().map(brand -> dtoAssembler.getBrandDTO(brand)).collect(Collectors.toList());
        } catch (Exception e){
            return null;
        }
    }
}
