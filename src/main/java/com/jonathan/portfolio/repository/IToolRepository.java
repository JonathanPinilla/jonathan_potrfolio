package com.jonathan.portfolio.repository;

import com.jonathan.portfolio.models.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IToolRepository extends MongoRepository<Tool, String> {
}
