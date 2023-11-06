package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IToolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

class GetAllToolsUseCaseTest {

    private final IToolRepository toolRepository = Mockito.mock(IToolRepository.class);
    private GetAllToolsUseCase getAllToolsUseCase;

    @BeforeEach
    public void setup() {

        getAllToolsUseCase = new GetAllToolsUseCase(toolRepository);
    }

    @Test
    void successfulScenario() {
        Tool tool1 = new Tool(
                UUID.randomUUID().toString(),
                "testTool1",
                4
        );
        Tool tool2 = new Tool(
                UUID.randomUUID().toString(),
                "testTool2",
                5
        );

        Mockito.when(toolRepository.findAll()).thenReturn(Arrays.asList(tool1, tool2));

        var result = getAllToolsUseCase.get();

        Assertions.assertEquals(tool1, result.get(0));
        Assertions.assertEquals(tool2, result.get(1));
        Assertions.assertEquals(tool1.id(), result.get(0).id());

    }

    @Test
    void databaseErrorScenario() {
        Mockito.when(toolRepository.findAll()).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, getAllToolsUseCase::get);
    }

    @Test
    void successfulScenarioGetAllById() {
        List<String> imagesId = Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        Tool tool1 = new Tool(
                UUID.randomUUID().toString(),
                "testTool1",
                4
        );
        Tool tool2 = new Tool(
                UUID.randomUUID().toString(),
                "testTool2",
                5
        );

        Mockito.when(toolRepository.findAllById(imagesId)).thenReturn(Arrays.asList(tool1, tool2));

        var result = getAllToolsUseCase.get(imagesId);

        Assertions.assertEquals(tool1, result.get(0));
        Assertions.assertEquals(tool2, result.get(1));
        Assertions.assertEquals(tool1.id(), result.get(0).id());
    }

    @Test
    void databaseErrorScenarioGetAllById() {
        List<String> imagesId = Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        Mockito.when(toolRepository.findAllById(imagesId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> getAllToolsUseCase.get(imagesId));

    }
}