package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.ImageRepository;
import com.example.serverapi.database.repository.ListingRepository;
import com.example.serverapi.dto.ImageDTO;
import com.example.serverapi.model.Image;
import com.example.serverapi.model.Listing;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final DtoAssembler dtoAssembler;
    private final ListingRepository listingRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, DtoAssembler dtoAssembler, ListingRepository listingRepository) {
        this.imageRepository = imageRepository;
        this.dtoAssembler = dtoAssembler;
        this.listingRepository = listingRepository;
    }

    @Transactional
    public Image createOrUpdateImage(ImageDTO imageDTO) {
        try{

            Image image = dtoAssembler.getImageEntity(imageDTO);
            if(imageDTO.getListingId() != null ) {
                Optional<Listing> existence = listingRepository.findById(imageDTO.getListingId());
                if (existence.isPresent()) {
                    Listing listing = existence.get();
                    image.setListing(listing);
                }
            }


            return imageRepository.save(image);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public Image createOrUpdateImage(Image image) {
        return imageRepository.save(image);
    }

    public ImageDTO getImageDTOById(Long id) {
        try{
            Optional<Image> existence = imageRepository.findById(id);
            if(existence.isPresent()){
                Image image = existence.get();
                ImageDTO imageDTO = dtoAssembler.getImageDTO(image);
                return imageDTO;
            }
            else{
                throw new EntityNotFoundException("Image not found");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public Optional<Image>  getImageById(Long id) {
        return imageRepository.findById(id);
    }


    public Image getImageByImageUrl(String imageUrl) {
        return imageRepository.findByImageUrl(imageUrl);
    }

    public void deleteImageById(Long id) {
        imageRepository.deleteById(id);
    }


}
