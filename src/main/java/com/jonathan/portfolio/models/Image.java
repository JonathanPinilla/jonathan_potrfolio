package com.jonathan.portfolio.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "images")
public record Image(
        @Id
        String id,
        String name,
        String url,
        String alternativeText
) {
    public Image(String name, String url, String alternativeText) {
        this(UUID.randomUUID().toString(), name, url, alternativeText);
    }
}
