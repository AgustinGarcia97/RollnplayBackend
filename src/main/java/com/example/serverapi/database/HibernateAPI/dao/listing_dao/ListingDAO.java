package com.example.serverapi.database.HibernateAPI.dao.listing_dao;

import com.example.serverapi.model.Listing;

import java.util.Map;

public interface ListingDAO {

    Listing createListing(Listing listing);
    Listing updateListing(Long id, Map<String,Object> fieldUpdates);
    Listing getListingById(Long id);
    void  deleteListing(Long id);
}
