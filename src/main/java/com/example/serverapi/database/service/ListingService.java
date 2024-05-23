package com.example.serverapi.database.service;


import com.example.serverapi.database.repository.ListingRepository;
import com.example.serverapi.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;


    public void createListing(Listing listing) {
        listingRepository.save(listing);
    }





}
