package com.jonathan.portfolio.usecases.image;

import com.jonathan.portfolio.models.Image;
import com.jonathan.portfolio.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveImagesUseCase {


    private final IImageRepository imageRepository;

    @Autowired
    public SaveImagesUseCase(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> save(List<Image> images) {
        try {
            return imageRepository.saveAll(images);
        } catch (Exception e) {
            throw new RuntimeException("The database transaction failed: " + e.getMessage());
        }
    }

}
