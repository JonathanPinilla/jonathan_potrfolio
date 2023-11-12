package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IToolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

class SaveToolUseCaseTest {

    private final IToolRepository toolRepository = Mockito.mock(IToolRepository.class);
    private SaveToolUseCase saveToolUseCase;

    @BeforeEach
    public void setup() {
        saveToolUseCase = new SaveToolUseCase(toolRepository);
    }

    @Test
    public void successfulScenario() {
        Tool tool = new Tool(
                "testTool",
                5
        );

        Mockito.when(saveToolUseCase.save(tool)).thenReturn(tool);

        Tool result = saveToolUseCase.save(tool);

        Assertions.assertEquals(tool.id(), result.id());
        Assertions.assertEquals(tool.name(), result.name());

    }

    @Test
    public void databaseErrorScenario() {
        Tool tool = new Tool(
                "testTool",
                5
        );

        Mockito.when(saveToolUseCase.save(tool)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> saveToolUseCase.save(tool));

    }

    public void saveListSuccessfulScenario() {
        Tool tool = new Tool(
                "testTool",
                5
        );
        Tool tool2 = new Tool(
                "testTool2",
                5
        );

        List<Tool> tools = List.of(tool, tool2);

        Mockito.when(saveToolUseCase.save(tools)).thenReturn(tools);

        List<Tool> result = saveToolUseCase.save(tools);

        Assertions.assertEquals(tools.get(0).id(), result.get(0).id());
        Assertions.assertEquals(tools.get(0).name(), result.get(0).name());
        Assertions.assertEquals(tools.get(1).id(), result.get(1).id());
        Assertions.assertEquals(tools.get(1).name(), result.get(1).name());
    }

    @Test
    public void saveListDatabaseErrorScenario() {
        Tool tool = new Tool(
                "testTool",
                5
        );
        Tool tool2 = new Tool(
                "testTool2",
                5
        );

        List<Tool> tools = List.of(tool, tool2);

        Mockito.when(saveToolUseCase.save(tools)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> saveToolUseCase.save(tools));
    }

}