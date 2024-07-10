package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.SaleRepository;
import com.example.serverapi.dto.ListingDTO;
import com.example.serverapi.dto.SaleDTO;
import com.example.serverapi.model.Listing;
import com.example.serverapi.model.Purchase;
import com.example.serverapi.model.Sale;
import com.example.serverapi.model.User;
import com.example.serverapi.utils.Converter.DtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SaleService {


    private final UserService userService;
    private final SaleRepository saleRepository;
    private final ListingService saleService;
    private final ListingService listingService;
    private DtoAssembler dtoAssembler;


    @Autowired
    public SaleService(UserService userService, SaleRepository saleRepository,
                       ListingService saleService, DtoAssembler dtoAssembler, ListingService listingService) {
        this.userService = userService;
        this.saleRepository = saleRepository;
        this.saleService = saleService;
        this.dtoAssembler = dtoAssembler;
        this.listingService = listingService;
    }

    public void saveSale(Sale sale){
        saleRepository.save(sale);
    }

    public Sale createSale(SaleDTO saleDTO){
        /**
         * 1) extraer las publicaciones
         * 2) extraer los usuarios
         * 3)checkear stock en todas las publicaciones
         * 4.1.1) si da ok -> calcular el total de la venta
         * 4.1.2) si da ok ->  actualizar stock
         * 4.2.1) si no da ok -> return null
         * 5) convertir saleDTO a entidad
         * 6) guardar sale en user1,user2, y listing
         */
        Sale sale = new Sale();

        List <User> seller = saleDTO.getListingsDTO().stream()
                .map(listingDTO -> //listingDTO tiene el id del vendedor, entonces lo busco con userService
                        userService.getUserById(listingDTO.getUserId()).get())
                .collect(Collectors.toList());

        User buyer = dtoAssembler.getUserEntity(userService.getUserDTOById(saleDTO.getBuyer()));
        List<ListingDTO> listingsDTO = saleDTO.getListingsDTO();

        double totalPrice = listingsDTO.stream()
                .mapToDouble(listingDTO -> listingDTO.getPrice() * listingDTO.getQuantity())
                .sum();
        try{
            List<Listing> listings = saleDTO.getListingsDTO()
                    .stream()
                    .map(listingDTO -> {
                                Listing listing = listingService.getListingEntityById(listingDTO.getListingId());
                                double updatedStock = listing.getStock() - listingDTO.getQuantity();
                                if (listing.getStock() > updatedStock && listing.getStock() > 0) {
                                    listing.setStock(updatedStock);
                                    listing.setQuantity(listingDTO.getQuantity());
                                    return listing;
                                } else {
                                    throw new RuntimeException("Stock exceeds stock");
                                }
                            }
                    )
                    .toList();
            try{
                if(!listings.isEmpty()){
                    sale.setSaleDate(LocalDateTime.now());
                    sale.setBuyer(buyer);
                    sale.setSeller(seller);
                    listings.forEach(listing -> {
                        sale.setListingsSales(listing);
                        sale.addListingQuantity(listing);
                    });
                    sale.setPrice(totalPrice);
                    saleRepository.save(sale);

                    return sale;
                }
            } catch  (Exception ignored){
                return null;
            }

            return sale;
        } catch (Exception e) {
            return null;
        }

    }

    public List<SaleDTO> getAllSalesDTOByUserId(UUID userId){
        return saleRepository.findAll()
                .stream()
                .filter(sale ->
                        sale.getSeller()
                                .stream()
                                .anyMatch(user -> user.getUserId().equals(userId))
                        &&
                                sale.getListingsSales()
                                        .stream()
                                        .anyMatch(listing -> listing.getUser().getUserId().equals(userId)))


                .map(sale -> dtoAssembler.getSaleDTO(sale))
                .collect(Collectors.toList());

    }

    public List<SaleDTO>getAllPurchasesDTOByUserId(UUID userId) {
        return saleRepository
                .findAll()
                .stream()
                .filter(sale ->
                        sale.getBuyer().getUserId().equals(userId))
                .map(dtoAssembler::getSaleDTO)
                .collect(Collectors.toList());
    }


}
