package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.PlayerRepository;
import com.example.serverapi.database.repository.ProductRepository;
import com.example.serverapi.dto.ProductDTO;
import com.example.serverapi.exceptions.CustomDatabaseException;
import com.example.serverapi.model.*;


import com.example.serverapi.utils.Converter.DtoAssembler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {




    private ProductRepository productRepository;

    private final CategoryService categoryService;

    private final PlayerService playerService;

    private final BrandService brandService;

    private final DurationService durationService;

    private final DifficultyService difficultyService;

    private DtoAssembler dtoAssembler;





    @Autowired
    public ProductService(ProductRepository productRepository, DtoAssembler dtoAssembler,
                          CategoryService categoryService, PlayerService playerService,
                          BrandService brandService, DurationService durationService,
                          DifficultyService difficultyService) {

        this.productRepository = productRepository;
        this.dtoAssembler = dtoAssembler;
        this.categoryService = categoryService;
        this.playerService = playerService;
        this.brandService = brandService;
        this.durationService = durationService;
        this.difficultyService = difficultyService;
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Optional<ProductDTO> findByIdDTO(long id) {
        ProductDTO productDTO = null;
        try {
            Optional<Product> existence = productRepository.findById(id);
            if (existence.isPresent()) {
                productDTO = dtoAssembler.getProductDTO(existence.get());
                productDTO.setProductCategory(dtoAssembler.getCategoryDTO(existence.get().getCategory()));
                productDTO.setProductPlayers(dtoAssembler.getPlayerDTO(existence.get().getPlayers()));
            }
            else {
                throw new EntityNotFoundException("Product with id " + id + " not found");
            }

        }
        catch(EntityNotFoundException e){
            return Optional.empty();
        }
        return Optional.ofNullable(productDTO);

    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Transactional
    public Product createOrUpdateProduct(ProductDTO productDTO) {
        // en caso de que no exista player o category deberia persistirla antes en la db
        Product product = null;
        Category category = null;
        Player player = null;
        Brand brand;
        Duration duration = null;
        Difficulty difficulty = null;

        try{


                if(productDTO.getProductCategory().getCategoryId() == null){
                    category = categoryService.createOrUpdateCategory(productDTO.getProductCategory());
                } else {
                    Optional<Category> existence = categoryService.getCategoryById(productDTO.getProductCategory().getCategoryId());
                    if(existence.isPresent()){
                        category = existence.get();
                    }
                }

                if(productDTO.getProductPlayers().getPlayerId() == null){
                    player = playerService.createOrUpdatePlayer(productDTO.getProductPlayers());
                } else {
                    Optional<Player> existence = playerService.getPlayerById(productDTO.getProductPlayers().getPlayerId());
                    if(existence.isPresent()){
                        player = existence.get();
                    }
                }

                if(productDTO.getProductBrand().getBrandId() == null){
                    brand = brandService.createOrUpdateBrand(productDTO.getProductBrand());
                } else {
                    brand = brandService.getBrandById(productDTO.getProductBrand().getBrandId());
                }

                if(productDTO.getDurationDTO().getId() == null ) {
                    duration = durationService.createOrUpdateDuration(productDTO.getDurationDTO());
                } else{
                    Optional<Duration> existence = durationService.getDurationById(productDTO.getDurationDTO().getId());
                    if(existence.isPresent()){
                        duration = existence.get();
                    }
                }

                if(productDTO.getDifficultyDTO().getId() == null ) {
                    difficulty = difficultyService.createOrUpdateDifficulty(productDTO.getDifficultyDTO());
                } else {
                    Optional<Difficulty> existence = difficultyService.getDifficultyDTOById(productDTO.getDifficultyDTO().getId());
                    if(existence.isPresent()){
                        difficulty = existence.get();
                    }
                }

                product = dtoAssembler.getProductEntity(productDTO);
                product.setCategory(category);
                product.setPlayers(player);
                product.setProductBrand(brand);
                product.setDuration(duration);
                product.setDifficulty(difficulty);

            product = productRepository.save(product);


        }
        catch(HibernateException e){
            logger.error("Error saving product: {}",product, e);
            throw new CustomDatabaseException("Error saving product:",e);
        }

        return product;
    }


    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }



}
