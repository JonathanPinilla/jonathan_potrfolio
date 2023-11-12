package com.jonathan.portfolio.dto;

import com.jonathan.portfolio.models.Image;
import com.jonathan.portfolio.models.Tool;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProjectDTO(
        String id,
        String description,
        List<Image> images,
        LocalDate date,
        List<Tool> tools
) {
    public ProjectDTO(String description, List<Image> images, LocalDate date, List<Tool> tools) {
        this(
                UUID.randomUUID().toString(),
                description,
                images,
                date,
                tools
        );
    }
}
