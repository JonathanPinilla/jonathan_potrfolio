package com.jonathan.portfolio.usecases.tool;

import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveToolUseCase {

    private final IToolRepository toolRepository;

    @Autowired
    public SaveToolUseCase(IToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public Tool save(Tool tool) {
        try {
            return toolRepository.save(tool);
        } catch (Exception e) {
            throw new RuntimeException("The database transaction failed: " + e.getMessage());
        }
    }

    public List<Tool> save(List<Tool> tools) {
        try {
            return toolRepository.saveAll(tools);
        } catch (Exception e) {
            throw new RuntimeException("The database transaction failed: " + e.getMessage());
        }
    }
}
