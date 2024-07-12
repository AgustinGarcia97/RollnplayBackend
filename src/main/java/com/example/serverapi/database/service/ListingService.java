package com.example.serverapi.database.service;


import com.example.serverapi.database.repository.ListingRepository;
import com.example.serverapi.database.repository.SaleRepository;
import com.example.serverapi.database.repository.UserRepository;
import com.example.serverapi.dto.ImageDTO;
import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.exceptions.dtoExceptions.ConversionException;
import com.example.serverapi.exceptions.userExceptions.UserNotFoundException;
import com.example.serverapi.model.*;

import com.example.serverapi.utils.Converter.DtoAssembler;
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
    private final SaleRepository saleRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private ListingRepository listingRepository;
    private ImageService imageService;
    private DtoAssembler dtoAssembler;

    @Autowired
    public ListingService(SaleRepository saleRepository, ListingRepository listingRepository, DtoAssembler dtoAssembler,
                          ProductService productService, UserService userService, ImageService imageService, UserRepository userRepository){
        this.saleRepository = saleRepository;
        this.listingRepository = listingRepository;
        this.dtoAssembler = dtoAssembler;
        this.productService = productService;
        this.userService = userService;
        this.imageService = imageService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createOrUpdateListing(ListingDTO listingDTO) {
        Listing listing = null;
        try {
            listing = dtoAssembler.getListingEntity(listingDTO);

            Product product = null;
            List<Image> images = new ArrayList<>();
            List<ImageDTO> imagesDTO = new ArrayList<>();
            if(listingDTO.getProductDTO().getProductId() != null) {
                Optional<Product> existence = productService.findById(listingDTO.getProductDTO().getProductId());
                //Si el producto existe lo obtiene, sino crea uno nuevo
                if (existence.isPresent()) {
                    product = existence.get();
                }
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

    public Listing getListingEntityById(long id){
        return listingRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteListing(long id) {
        Listing listing = getListingEntityById(id);
        if(listing.getSales() != null && !listing.getSales().isEmpty()) {
            listing.preRemove();
            saleRepository.saveAll(listing.getSales());
        }


        listingRepository.deleteById(id);
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Optional<Listing> getListingById(long id) {
        return listingRepository.findById(id);
    }


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

    @Transactional
    public List<ListingDTO> getListingsDTOByProductName(String productName) {
        List<ListingDTO> listingsDTO = null;
        System.out.println(productName);
        List<Listing> list = listingRepository.findAll();
        try{
            listingsDTO = listingRepository.findAll().stream()
                    .filter(listing -> listing.getProduct().getProductName().equalsIgnoreCase(productName))
                    .map(listing -> dtoAssembler.getListingDTO(listing))
                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }

    public List<ListingDTO> getListingsDTOByCategory(String category) {
        List<ListingDTO> listingsDTO = null;

        try{
            listingsDTO = this.getAllListings().stream()
                    .map(listing -> dtoAssembler.getListingDTO(listing))
                    .filter(listingDTO -> listingDTO.getProductDTO().getCategoryName().equals(category))
                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }

    public List<ListingDTO> getListingsDTOByBrand(String brand) {
        List<ListingDTO> listingsDTO = null;

        try{
            listingsDTO = this.getAllListings().stream()
                    .filter(listing -> listing.getProduct().getProductBrand().getBrandName().equals(brand))
                    .map(listing -> dtoAssembler.getListingDTO(listing))
                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }

    public List<ListingDTO> getListingsDTOByDuration(String duration) {
        List<ListingDTO> listingsDTO = null;

        try{
            listingsDTO = this.getAllListings().stream()
                    .filter(listing -> listing.getProduct().getDuration().getDurationName().equals(duration))
                    .map(listing -> dtoAssembler.getListingDTO(listing))

                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }

    public List<ListingDTO> getListingsDTOByDifficulty(String difficulty) {
        List<ListingDTO> listingsDTO = null;

        try{
            listingsDTO = this.getAllListings().stream()
                    .filter(listing -> listing.getProduct().getDifficulty().getDifficultyName().equals(difficulty))
                    .map(listing -> dtoAssembler.getListingDTO(listing))
                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }

    public List<ListingDTO> getListingsDTOByPlayerCuantity(String players) {
        List<ListingDTO> listingsDTO = null;

        try{
            listingsDTO = this.getAllListings().stream()
                    .filter(listing -> listing.getProduct().getPlayers().getNumberOfPlayers().equals(players))
                    .map(listing -> dtoAssembler.getListingDTO(listing))

                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }

    public List<ListingDTO> getListingsDTOByPrice(double price) {
        List<ListingDTO> listingsDTO = null;

        try{
            listingsDTO = this.getAllListings().stream()
                    .map(listing -> dtoAssembler.getListingDTO(listing))
                    .filter(listingDTO -> listingDTO.getPrice() <= price)
                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }


    public List<ListingDTO> getListingsDTOByUser(String email) {
        List<ListingDTO> listingsDTO = null;

        try{
            listingsDTO = this.getAllListings().stream()
                    .filter(listing -> listing.getUser().getUsername().equals(email))
                    .map(listing -> dtoAssembler.getListingDTO(listing))
                    .collect(Collectors.toList());

        } catch(Exception e){
            e.printStackTrace();
        }
        return listingsDTO;

    }

}
