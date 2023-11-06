package com.jonathan.portfolio.usecases.image;

import com.jonathan.portfolio.models.Image;
import com.jonathan.portfolio.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllImagesUseCase {

    private final IImageRepository imageRepository;

    @Autowired
    public GetAllImagesUseCase(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> get(List<String> imagesId) {
        try {
            return imageRepository.findAllById(imagesId);
        } catch (Exception e) {
            throw new RuntimeException("The database transaction failed: " + e.getMessage());
        }
    }
}
