package com.example.serverapi.database.repository;

import com.example.serverapi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByImageUrl(String imageUrl);
}
