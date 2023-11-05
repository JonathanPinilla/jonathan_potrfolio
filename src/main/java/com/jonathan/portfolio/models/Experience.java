package com.jonathan.portfolio.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "experience")
public record Experience(
        @Id
        String id,
        String company,
        String jobTitle,
        String jobDescription,
        String contact,
        LocalDate startDate,
        LocalDate endDate,
        List<Tool> tools

) {
}
