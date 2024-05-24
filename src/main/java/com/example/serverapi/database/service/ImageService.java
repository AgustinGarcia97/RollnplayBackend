package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.ImageRepository;
import com.example.serverapi.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void createOrUpdate(Image image) {
        imageRepository.save(image);
    }

    public Image getImageByImageUrl(String imageUrl) {
        return imageRepository.findByImageUrl(imageUrl);
    }

    public void deleteImage(Image image) {
        imageRepository.delete(image);
    }
}
