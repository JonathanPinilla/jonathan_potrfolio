package com.jonathan.portfolio.usecases.image;

import com.jonathan.portfolio.models.Image;
import com.jonathan.portfolio.repository.IImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class GetAllImagesUseCaseTest {

    private final IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
    private GetAllImagesUseCase getAllImagesUseCase;

    @BeforeEach
    void setUp() {
        getAllImagesUseCase = new GetAllImagesUseCase(imageRepository);
    }

    @Test
    void successfulScenario() {
        Image image = new Image("id", "name", "url", "alt");
        Image image2 = new Image("id2", "name2", "url2", "alt2");

        Mockito.when(imageRepository.findAllById(Mockito.anyList())).thenReturn(
                List.of(image, image2)
        );

        List<Image> images = getAllImagesUseCase.get(List.of("id", "id2"));

        Assertions.assertEquals(2, images.size());
        Assertions.assertEquals("id", images.get(0).id());
        Assertions.assertEquals("name2", images.get(1).name());

    }

    @Test
    void databaseErrorScenario() {
        Mockito.when(imageRepository.findAllById(Mockito.anyList())).thenThrow(
                new RuntimeException("Database error")
        );

        Assertions.assertThrows(RuntimeException.class, () -> getAllImagesUseCase.get(List.of("id", "id2")));
    }
}