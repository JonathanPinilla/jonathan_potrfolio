package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IToolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllToolsUseCase {

    private final IToolRepository toolRepository;

    public GetAllToolsUseCase(IToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> get() {
        try {
            return toolRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("The database did not respond: " + e.getMessage());
        }
    }
}
