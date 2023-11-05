package com.jonathan.portfolio.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public record Image(
        @Id
        String id,
        String name,
        String url,
        String alternativeText
) {
}
