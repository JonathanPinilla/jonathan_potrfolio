package com.jonathan.portfolio.repository;

import com.jonathan.portfolio.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectsRepository extends MongoRepository<Project, String> {
}
