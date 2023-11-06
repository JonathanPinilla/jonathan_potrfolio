package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IToolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
                UUID.randomUUID().toString(),
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
                UUID.randomUUID().toString(),
                "testTool",
                5
        );

        Mockito.when(saveToolUseCase.save(tool)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> saveToolUseCase.save(tool));

    }

}