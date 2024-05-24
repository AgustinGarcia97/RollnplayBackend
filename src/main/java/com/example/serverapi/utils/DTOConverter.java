package com.example.serverapi.utils;

import com.example.serverapi.database.service.ImageService;
import com.example.serverapi.database.service.ListingService;
import com.example.serverapi.database.service.ProductService;
import com.example.serverapi.database.service.UserService;
import com.example.serverapi.dto.*;
import com.example.serverapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DTOConverter {


    private final UserService userService;
    private final ProductService productService;
    private final ListingService listingService;
    private final ImageService imageService;
    @Autowired
    public DTOConverter(UserService userService, ProductService productService, ListingService listingService, ImageService imageService) {
        this.userService = userService;
        this.productService = productService;
        this.listingService = listingService;

        this.imageService = imageService;
    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setMail(user.getEmail());
        userDTO.setUsername(user.getUsername());

        userDTO.setListingsDTO(user.getListings()
                .stream()
                .map(this::convertToListingDTO)
                .collect(Collectors.toList()));

        userDTO.setPurchasesDTO(user.getPurchases()
                .stream()
                .map(this::convertToPurchaseDTO)
                .collect(Collectors.toList())
        );

        userDTO.setSalesDTO(user.getSales()
                .stream()
                .map(this::convertToSaleDTO)
                .collect(Collectors.toList())
        );

        return userDTO;
    }

    public ListingDTO convertToListingDTO(Listing listing) {
        ListingDTO listingDTO = new ListingDTO();
       // listingDTO.setListingId(listing.getListingId());
        listingDTO.setTitle(listing.getTitle());
        listingDTO.setDescription(listing.getDescription());
        listingDTO.setPrice(listing.getPrice());
        listingDTO.setStock(listing.getStock());
        listingDTO.setProductDTO(convertToProductDTO(listing.getProduct()));
        /*
        listingDTO.setSalesId(listing.getSales()
                .stream()
                .map(Sale::getSaleId)
                .collect(Collectors.toList())
        );

        listingDTO.setPurchaseId(listing.getPurchases()
                .stream()
                .map(Purchase::getPurchaseId)
                .collect(Collectors.toList())
        );
        */

        return listingDTO;
    }

    public PurchaseDTO convertToPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setPurchaseId(purchase.getPurchaseId());
        purchaseDTO.setUserId(purchase.getUser().getUserId());
        purchaseDTO.setPurchaseDateTime(purchase.getPurchaseDateTime());
        purchaseDTO.setListingIds(
                purchase.getListingsPurchases()
                        .stream()
                        .map(Listing::getListingId)
                        .collect(Collectors.toList())

        );
        purchaseDTO.setProductIds(
                purchase.getProductsPurchases()
                        .stream()
                        .map(Product::getProductId)
                        .collect(Collectors.toList())
        );
        return purchaseDTO;
    }

    public ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductCategory(convertToCategoryDTO(product.getCategory()));
        productDTO.setProductPlayers(convertToPlayersDTO(product.getPlayers()));

        return productDTO;
    }

    public SaleDTO convertToSaleDTO(Sale sale){
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(sale.getSaleId());
        saleDTO.setSaleDate(sale.getSaleDate());
        saleDTO.setListingsId(
                sale.getListingsSales()
                        .stream()
                        .map(Listing::getListingId)
                        .collect(Collectors.toList())
        );
        saleDTO.setProductIds(
                sale.getProductsSales()
                        .stream()
                        .map(Product::getProductId)
                        .collect(Collectors.toList())
        );
        return saleDTO;
    }

    public CategoryDTO convertToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setProductsIds(category.getProducts()
                .stream()
                .map(Product::getProductId)
                .collect(Collectors.toList()));

        return categoryDTO;
    }

    public PlayerDTO convertToPlayersDTO(Player player){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(player.getPlayersId());
        playerDTO.setNumberOfPlayers(player.getNumberOfPlayers());
        playerDTO.setProductsId(player.getProduct()
                .stream()
                .map(Product::getProductId)
                .collect(Collectors.toList()));

        return playerDTO;
    }

    /**  DTO a Objeto  **/
    public Product convertToProduct(ProductDTO productDTO){
        Product product = new Product();
        if(productDTO.getProductId() != null || productDTO.getProductId() != 0L){
            product.setProductId(productDTO.getProductId());
        }
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setCategory(convertToCategory(productDTO.getProductCategory()));
        product.setPlayers(convertToPlayers(productDTO.getProductPlayers()));

        return product;
    }
    public Category convertToCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }

    public Player convertToPlayers(PlayerDTO playerDTO){
        Player player = new Player();
        player.setNumberOfPlayers(playerDTO.getNumberOfPlayers());

        return player;
    }


    public Listing convertToListing(ListingDTO listingDTO) {
        Listing listing = new Listing();
        Optional<Listing> request = listingService.getListingByListingId(listingDTO.getListingId());
        // en caso de querer actualizar un Listing, se busca para actualizar el existente, y no crear uno nuevo.
        //Al existir ya tiene un Id existente cargado, por eso no crea uno nuevo.
        if(request.isPresent()){
               listing = request.get();
           }

        listing.setTitle(listingDTO.getTitle());
        listing.setDescription(listingDTO.getDescription());
        listing.setPrice(listingDTO.getPrice());
        listing.setStock(listingDTO.getStock());
        listing.setState(listingDTO.getListingState());
        //listing.setUser(userService.getUserById(listingDTO.getUserId()));
        listing.setUser(null);
        Product product = productService.getProductByProductName(listingDTO.getProductDTO().getProductName());
        listing.setImages(
                listingDTO
                        .getImages()
                        .stream()
                        .map(this::convertToImage)
                        .collect(Collectors.toList())
        );

        //Si el producto buscado no es nulo, lo agrega. Esto tambien evita que no se cargue un registro duplicado en la tabla de Product
        if(product != null){
            listing.setProduct(product);
        }
        else{
            Product p =  convertToProduct(listingDTO.getProductDTO());
            listing.setProduct(p);
        }

        return listing;
    }

    public Image convertToImage(ImageDTO imageDTO){
        Image img = imageService.getImageByImageUrl(imageDTO.getImageUrl());
        if(img == null){
            img = new Image();
            img.setImageUrl(imageDTO.getImageUrl());
            Optional<Listing> request = (listingService.getListing(imageDTO.getListingId()));
            if(request.isPresent()){
                img.setListing(request.get());
            }
        }
        return img;

    }
}
