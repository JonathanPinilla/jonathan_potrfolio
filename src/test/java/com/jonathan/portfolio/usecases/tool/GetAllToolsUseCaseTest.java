package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IToolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

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
                "testTool1",
                4
        );
        Tool tool2 = new Tool(
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

        Tool tool1 = new Tool(
                "testTool1",
                4
        );
        Tool tool2 = new Tool(
                "testTool2",
                5
        );

        List<Tool> tools = Arrays.asList(tool1, tool2);

        List<String> toolsId = Arrays.asList(tool1.id(), tool2.id());

        Mockito.when(toolRepository.findAllById(toolsId)).thenReturn(tools);

        var result = getAllToolsUseCase.get(toolsId);

        Assertions.assertEquals(tool1, result.get(0));
        Assertions.assertEquals(tool2, result.get(1));
        Assertions.assertEquals(tool1.id(), result.get(0).id());
    }

    @Test
    void databaseErrorScenarioGetAllById() {

        Tool tool1 = new Tool(
                "testTool1",
                4
        );
        Tool tool2 = new Tool(
                "testTool2",
                5
        );

        List<String> toolsId = Arrays.asList(tool1.id(), tool2.id());

        Mockito.when(toolRepository.findAllById(toolsId)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> getAllToolsUseCase.get(toolsId));

    }
}