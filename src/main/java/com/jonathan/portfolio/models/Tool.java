package com.jonathan.portfolio.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "collections")
public record Tool(
        @Id
        String id,
        String name,
        Integer level
) {
}
