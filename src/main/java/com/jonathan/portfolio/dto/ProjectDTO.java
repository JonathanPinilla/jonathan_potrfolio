package com.jonathan.portfolio.dto;

import com.jonathan.portfolio.models.Tool;

import java.time.LocalDate;
import java.util.List;

public record ProjectDTO(
        String id,
        String description,
        List<String> imagesId,
        LocalDate date,
        List<Tool> tools
) {
}
