package com.jonathan.portfolio.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "projects")
public record Project(
        @Id
        String id,
        String description,
        List<String> imagesId,
        LocalDate date,
        List<String> toolsId
) {
}
