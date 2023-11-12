package com.jonathan.portfolio.usecases.image;

import com.jonathan.portfolio.models.Image;
import com.jonathan.portfolio.repository.IImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveImagesUseCaseTest {

    private final IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
    private SaveImagesUseCase saveImagesUseCase;

    @BeforeEach
    void setUp() {
        saveImagesUseCase = new SaveImagesUseCase(imageRepository);
    }

    @Test
    void successfulScenario() {

        Image image = new Image(
                "url",
                "someURl",
                "alt"
        );
        Image image2 = new Image(
                "url2",
                "someURl2",
                "alt2"
        );

        List<Image> images = List.of(image, image2);

        Mockito.when(saveImagesUseCase.save(images)).thenReturn(images);

        List<Image> result = saveImagesUseCase.save(images);

        Assertions.assertEquals(images, result);
        Assertions.assertEquals(images.get(0).id(), result.get(0).id());

    }

    @Test
    public void databaseErrorScenario() {
        Image image = new Image(
                "url",
                "someURl",
                "alt"
        );
        Image image2 = new Image(
                "url2",
                "someURl2",
                "alt2"
        );

        List<Image> images = List.of(image, image2);

        Mockito.when(saveImagesUseCase.save(images)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> saveImagesUseCase.save(images));
    }
}