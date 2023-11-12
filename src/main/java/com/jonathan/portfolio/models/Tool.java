package com.jonathan.portfolio.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "collections")
public record Tool(
        @Id
        String id,
        String name,
        Integer level
) {
        public Tool(String name, Integer level) {
                this(
                        UUID.randomUUID().toString(),
                        name,
                        level
                );
        }
}
