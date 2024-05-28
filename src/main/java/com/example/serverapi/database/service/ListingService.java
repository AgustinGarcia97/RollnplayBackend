package com.example.serverapi.database.service;


import com.example.serverapi.database.repository.ListingRepository;
import com.example.serverapi.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;


    public void createOrUpdateListing(Listing listing) {
        listingRepository.save(listing);
    }

    public Optional<Listing> getListing(long id) {
        return listingRepository.findById(id);
    }


    public Optional<Listing> getListingByListingId(long listingId) {
        return listingRepository.findById(listingId);
    }

    public void deleteListing(long id) {
        listingRepository.deleteById(id);
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }


}
