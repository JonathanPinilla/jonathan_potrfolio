package com.jonathan.portfolio.repository;

import com.jonathan.portfolio.models.Study;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudyRepository extends MongoRepository<Study, String> {
}
