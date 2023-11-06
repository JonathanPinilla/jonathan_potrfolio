package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.repository.IToolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;


class DeleteToolUseCaseTest {

    private final IToolRepository toolRepository = Mockito.mock(IToolRepository.class);
    private DeleteToolUseCase deleteToolUseCase;

    @BeforeEach
    void setUp() {
        deleteToolUseCase = new DeleteToolUseCase(toolRepository);
    }

    @Test
    void successfulScenario() {
        String id = UUID.randomUUID().toString();

        deleteToolUseCase.delete(id);

        Mockito.verify(toolRepository).deleteById(id);

    }

    @Test
    public void testDeleteToolFailure() {
        String id = UUID.randomUUID().toString();

        Mockito.doThrow(new RuntimeException("Simulated database failure")).when(toolRepository).deleteById(id);

        Assertions.assertThrows(RuntimeException.class, () -> deleteToolUseCase.delete(id));
    }
}