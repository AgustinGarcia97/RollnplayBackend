package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.SaleListingRepository;
import com.example.serverapi.model.SaleListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleListingService {
    @Autowired
    private SaleListingRepository saleListingRepository;

    public void loadSaleListing(Long sale_id, Long listing_id) {
        saleListingRepository.save(new SaleListing(sale_id, listing_id));
    }

    public Integer countListingProduct(Long listing_id, Long product_id) {
        return saleListingRepository.countByListingAndProduct(listing_id, product_id);
    }

}
