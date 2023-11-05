package com.jonathan.portfolio.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "studies")
public record Study(
        @Id
        String id,
        String institution,
        String degree,
        LocalDate endDate
) {
}
