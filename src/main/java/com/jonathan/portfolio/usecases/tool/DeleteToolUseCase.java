package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.repository.IToolRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteToolUseCase {

    private final IToolRepository toolRepository;

    public DeleteToolUseCase(IToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public void delete(String id) {
        try {
            toolRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("The database transaction failed: " + e.getMessage());
        }
    }
}
