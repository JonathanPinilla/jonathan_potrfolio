package com.jonathan.portfolio.repository;

import com.jonathan.portfolio.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends MongoRepository<Image, String> {
}
