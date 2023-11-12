package com.jonathan.portfolio.usecases.project;

import com.jonathan.portfolio.dto.ProjectDTO;
import com.jonathan.portfolio.models.Image;
import com.jonathan.portfolio.models.Project;
import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IProjectsRepository;
import com.jonathan.portfolio.usecases.image.SaveImagesUseCase;
import com.jonathan.portfolio.usecases.tool.SaveToolUseCase;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SaveProjectUseCase {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(SaveProjectUseCase.class);
    private final IProjectsRepository projectsRepository;
    private final SaveImagesUseCase saveImagesUseCase;
    private final SaveToolUseCase saveToolUseCase;

    @Autowired
    public SaveProjectUseCase(
            IProjectsRepository projectsRepository,
            SaveImagesUseCase saveImagesUseCase,
            SaveToolUseCase saveToolUseCase
    ) {
        this.projectsRepository = projectsRepository;
        this.saveImagesUseCase = saveImagesUseCase;
        this.saveToolUseCase = saveToolUseCase;
    }

    public void save(ProjectDTO projectDTO) {
        logger.info("Saving Project: " + projectDTO.id());
        try {
            saveImagesUseCase.save(projectDTO.images());
            saveToolUseCase.save(projectDTO.tools());

            logger.info("Performing database save transaction for Project: " + projectDTO.id());
            projectsRepository.save(new Project(
                    projectDTO.id(),
                    projectDTO.description(),
                    projectDTO.images().stream().map(Image::id).toList(),
                    projectDTO.date(),
                    projectDTO.tools().stream().map(Tool::id).toList()
            ));
        } catch (Exception e) {
            throw new RuntimeException("The database transaction failed: " + e.getMessage());
        }
    }

}
