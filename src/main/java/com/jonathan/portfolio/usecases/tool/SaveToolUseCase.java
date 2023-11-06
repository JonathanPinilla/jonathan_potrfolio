package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IToolRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveToolUseCase {

    private final IToolRepository toolRepository;

    public SaveToolUseCase(IToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public Tool save(Tool tool) {
        try {
            return toolRepository.save(tool);
        } catch (Exception e) {
            throw new RuntimeException("The database did not respond: " + e.getMessage());
        }
    }
}
