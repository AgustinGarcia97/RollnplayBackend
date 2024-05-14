package com.example.serverapi.database.dao.listing_dao;

import com.example.serverapi.model.Listing;

import java.util.Map;

public interface ListingDAO {

    Listing createListing(Listing listing);
    Listing updateListing(String id, Map<String,Object> fieldUpdates);
    Listing getListingById(String id);
    void  deleteListing(String id);
}
