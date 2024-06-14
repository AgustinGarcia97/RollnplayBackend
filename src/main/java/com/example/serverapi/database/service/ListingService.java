package com.example.serverapi.database.service;


import com.example.serverapi.database.repository.ListingRepository;
import com.example.serverapi.dto.ImageDTO;
import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.exceptions.userExceptions.UserNotFoundException;
import com.example.serverapi.model.Image;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.Product;
import com.example.serverapi.model.User;
import com.example.serverapi.utils.converter.DtoAssembler;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListingService {

    private final ProductService productService;
    private final UserService userService;
    private ListingRepository listingRepository;
    private ImageService imageService;
    private DtoAssembler dtoAssembler;

    @Autowired
    public ListingService(ListingRepository listingRepository, DtoAssembler dtoAssembler,
                          ProductService productService, UserService userService, ImageService imageService){
        this.listingRepository = listingRepository;
        this.dtoAssembler = dtoAssembler;
        this.productService = productService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @Transactional
    public Optional<?> createOrUpdateListing(ListingDTO listingDTO) {
        Listing listing = null;
        try {
            listing = dtoAssembler.getListingEntity(listingDTO);
            Product product;
            List<Image> images = new ArrayList<>();
            List<ImageDTO> imagesDTO = new ArrayList<>();
            Optional <Product> existence = productService.findById(listingDTO.getProductDTO().getProductId());
            //Si el producto existe lo obtiene, sino crea uno nuevo
            if (existence.isPresent()) {
                product = existence.get();
            } else {
                product = productService.createOrUpdateProduct(listingDTO.getProductDTO());
            }

            Optional<User> user = userService.getUserById(listingDTO.getUserId());
            if (user.isPresent()) {
                listing.setUser(user.get());
            } else {
                throw new UserNotFoundException("User not found");
            }

            if (listingDTO.getImages() != null && !listingDTO.getImages().isEmpty()) {
                imagesDTO = listingDTO.getImages();
                for (ImageDTO imageDTO : imagesDTO) {
                    Image image = dtoAssembler.getImageEntity(imageDTO);
                    images.add(image);
                    }
                }



            listing.setProduct(product);
            listing.setImages(images);
            listing = listingRepository.save(listing);

            for(Image image : listing.getImages()){
                if(image.getListing() == null){
                    image.setListing(listing);
                    imageService.createOrUpdateImage(image);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(listing);

    }

    public Optional<ListingDTO> getListing(long id) {
        ListingDTO listingDTO;
        try {
            Optional<Listing> listing = listingRepository.findById(id);
            if(listing.isPresent()) {
                listingDTO = dtoAssembler.getListingDTO(listing.get());
                listingDTO.setProductDTO(dtoAssembler.getProductDTO(listing.get().getProduct()));
                listingDTO.getProductDTO().setProductPlayers(dtoAssembler.getPlayerDTO(listing.get().getProduct().getPlayers()));
                listingDTO.getProductDTO().setProductCategory(dtoAssembler.getCategoryDTO(listing.get().getProduct().getCategory()));
                listingDTO.setUserId(listing.get().getUser().getUserId());
                listingDTO.setImages(listing.get().getImages().stream().map(image -> dtoAssembler.getImageDTO(image)).collect(Collectors.toList()));
                return Optional.of(listingDTO);
            }
            else{
                throw new EntityNotFoundException("Listing not found");
            }
        }
        catch(ConversionException e) {
            throw e;
        }
    }

    public void deleteListing(long id) {
        listingRepository.deleteById(id);
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Optional<Listing> getListingById(long id) {
        return listingRepository.findById(id);
    }

    @Transactional
    public Optional<List<ListingDTO>> getAllListingsDTO() {
        try{
            List<Listing> listings = listingRepository.findAll();
            List<ListingDTO> dtos = listings
                    .stream()
                    .map(listing -> dtoAssembler.getListingDTO(listing))
                    .collect(Collectors.toList());
            return Optional.ofNullable(dtos);


        }
        catch(ConversionException e) {
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }


}
