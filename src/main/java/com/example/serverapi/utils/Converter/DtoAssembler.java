package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.*;
import com.example.serverapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoAssembler {

    private final UserConverter userConverter;
    private final ListingConverter listingConverter;
    private final ProductConverter productConverter;
    private final CategoryConverter categoryConverter;
    private final PlayerConverter playerConverter;
    private final ImageConverter imageConverter;
    private final BrandConverter brandConverter;

    @Autowired
    public DtoAssembler(UserConverter userConverter, ListingConverter listingConverter,
                        ProductConverter productConverter, CategoryConverter categoryConverter,
                        PlayerConverter playerConverter, ImageConverter imageConverter,
                        BrandConverter brandConverter) {

        this.userConverter = userConverter;
        this.listingConverter = listingConverter;
        this.productConverter = productConverter;
        this.categoryConverter = categoryConverter;
        this.playerConverter = playerConverter;
        this.imageConverter = imageConverter;
        this.brandConverter = brandConverter;
    }

    public User getUserEntity(UserDTO userDTO) {
        User user = null;
        try{
            user = userConverter.convertToEntity(userDTO);

            if(userDTO.getListingsDTO() != null){
                user.setListings(
                        userDTO
                                .getListingsDTO()
                                .stream()
                                .map(this::getListingEntity)
                                .collect(Collectors.toList())
                );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public Listing getListingEntity(ListingDTO listingDTO) {
        Listing listing = null;
        try{
            listing = listingConverter.convertToEntity(listingDTO);

            if(listingDTO.getImages() != null) {
                listing.setImages(
                        listingDTO
                                .getImages()
                                .stream()
                                .map(this::getImageEntity)
                                .collect(Collectors.toList()));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listing;
    }

    public Product getProductEntity(ProductDTO productDTO) {
        Product product = null;
        try{
            product = productConverter.convertToEntity(productDTO);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return product;
    }

    public Player getPlayerEntity(PlayerDTO playerDTO) {
        Player player = null;
        try{
            player = playerConverter.convertToEntity(playerDTO);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return player;
    }

    public Brand getBrandEntity(BrandDTO brandDTO) {
        Brand brand = null;
        try{
            brand = brandConverter.convertToEntity(brandDTO);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return brand;
    }




    public Category getCategoryEntity(CategoryDTO categoryDTO) {
        Category category = null;
        try{
            category = categoryConverter.convertToEntity(categoryDTO);
        }
        catch(Exception e){
           System.err.println("Error!!!!: "+e);
        }
        return category;
    }

    public Image getImageEntity(ImageDTO imageDTO) {
        Image image = null;
        try{
            image = imageConverter.convertToEntity(imageDTO);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }


    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        try{
            userDTO = userConverter.convertToFullDTO(user);

            if(user.getListings() != null){
                List<ListingDTO> listingDTOList = user
                        .getListings()
                        .stream()
                        .map(this::getListingDTO)
                        .collect(Collectors.toList());

                userDTO.setListingsDTO(listingDTOList);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return userDTO;
    }

    public ListingDTO getListingDTO(Listing listing) {
        ListingDTO listingDTO = null;
        try{
            listingDTO = listingConverter.convertToDTO(listing);

            listingDTO.setProductDTO(getProductDTO(listing.getProduct()));

            if(listing.getImages() != null){
                listing
                        .getImages()
                        .stream()
                        .map(this::getImageDTO)
                        .collect(Collectors.toList());
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listingDTO;
    }

    public ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = null;
        try{
            productDTO = productConverter.convertToDTO(product);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return productDTO;
    }

    public ImageDTO getImageDTO(Image image) {
        ImageDTO imageDTO = null;
        try{
            imageDTO = imageConverter.convertToDTO(image);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return imageDTO;
    }

    public CategoryDTO getCategoryDTO(Category category) {
        CategoryDTO categoryDTO = null;
        try{
            categoryDTO = categoryConverter.convertToDTO(category);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return categoryDTO;
    }

    public PlayerDTO getPlayerDTO(Player player) {
        PlayerDTO playerDTO = null;
        try{
            playerDTO = playerConverter.convertToDTO(player);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return playerDTO;
    }

    public BrandDTO getBrandDTO(Brand brand) {
        BrandDTO brandDTO = null;
        try{
            brandDTO = brandConverter.convertToDto(brand);
        }
        catch(Exception e){

            e.printStackTrace();
        }
        return brandDTO;
    }



}
