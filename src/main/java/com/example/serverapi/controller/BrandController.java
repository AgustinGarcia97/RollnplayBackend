package com.example.serverapi.controller;

import com.example.serverapi.database.repository.BrandRepository;
import com.example.serverapi.database.service.BrandService;
import com.example.serverapi.dto.BrandDTO;
import com.example.serverapi.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/get-brands")
    public ResponseEntity<List<BrandDTO>> getBrands() {
        List<BrandDTO> brandsDTO = new ArrayList<BrandDTO>();
        try{
            brandsDTO = brandService.getAllBrandsDTO();
            return ResponseEntity.ok(brandsDTO);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }


}
