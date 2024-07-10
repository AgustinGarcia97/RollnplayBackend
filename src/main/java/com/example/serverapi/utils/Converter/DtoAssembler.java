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
    private final DurationConverter durationConverter;
    private final DifficultyConverter difficultyConverter;
    private final SaleConverter saleConverter;

    @Autowired
    public DtoAssembler(UserConverter userConverter, ListingConverter listingConverter,
                        ProductConverter productConverter, CategoryConverter categoryConverter,
                        PlayerConverter playerConverter, ImageConverter imageConverter,
                        BrandConverter brandConverter, DurationConverter durationConverter,
                        DifficultyConverter difficultyConverter, SaleConverter saleConverter) {

        this.userConverter = userConverter;
        this.listingConverter = listingConverter;
        this.productConverter = productConverter;
        this.categoryConverter = categoryConverter;
        this.playerConverter = playerConverter;
        this.imageConverter = imageConverter;
        this.brandConverter = brandConverter;
        this.durationConverter = durationConverter;
        this.difficultyConverter = difficultyConverter;
        this.saleConverter = saleConverter;
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

    public Difficulty getDifficultyEntity(DifficultyDTO difficultyDTO){
        Difficulty difficulty = null;
        try{
            difficulty = difficultyConverter.convertToEntity(difficultyDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return difficulty;
    }

    public Duration getDurationEntity(DurationDTO durationDTO){
        Duration duration = null;
        try{
            duration = durationConverter.convertToEntity(durationDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration;
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

            if( listing.getProduct().getProductBrand() != null ){
                listingDTO.getProductDTO().setBrandName(listing.getProduct().getProductBrand().getBrandName());
            }




            listingDTO.getProductDTO().setCategoryName(listing.getProduct().getCategory().getCategoryName());

            listingDTO.getProductDTO().setPlayerCounter(listing.getProduct().getPlayers().getNumberOfPlayers());

            listingDTO.getProductDTO().setProductBrand(this.getBrandDTO(listing.getProduct().getProductBrand()));

            listingDTO.setImages(listing.getImages().stream().map(image -> imageConverter.convertToDTO(image)).collect(Collectors.toList()));

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
            productDTO.setProductPlayers(getPlayerDTO(product.getPlayers()));
            productDTO.setProductCategory(getCategoryDTO(product.getCategory()));
            productDTO.setDifficultyDTO(getDifficultyDTO(product.getDifficulty()));
            productDTO.setDurationDTO(getDurationDTO(product.getDuration()));
            productDTO.setProductBrand(getBrandDTO(product.getProductBrand()));

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

    public DifficultyDTO getDifficultyDTO(Difficulty difficulty) {
        DifficultyDTO difficultyDTO = null;
        try{
            difficultyDTO = difficultyConverter.convertToDTO(difficulty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return difficultyDTO;
    }

    public DurationDTO getDurationDTO(Duration duration) {
        DurationDTO durationDTO = null;
        try{
            durationDTO = durationConverter.convertToDTO(duration);
        } catch (Exception e){
            e.printStackTrace();
        }
        return durationDTO;
    }

    public SaleDTO getSaleDTO(Sale sale){
        try{
            SaleDTO saleDTO = saleConverter.convertToSaleDTO(sale);
            saleDTO.setListingsDTO(sale.getListingsSales().stream().map(this::getListingDTO).collect(Collectors.toList()));
            saleDTO.getListingsDTO().stream().forEach(listingDTO -> listingDTO.setQuantity(sale.getListingQuantity(listingDTO.getListingId())));
            return saleDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
