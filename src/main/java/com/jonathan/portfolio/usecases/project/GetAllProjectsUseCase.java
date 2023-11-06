package com.jonathan.portfolio.usecases.project;

import com.jonathan.portfolio.dto.ProjectDTO;
import com.jonathan.portfolio.models.Project;
import com.jonathan.portfolio.repository.IImageRepository;
import com.jonathan.portfolio.repository.IProjectsRepository;
import com.jonathan.portfolio.repository.IToolRepository;
import com.jonathan.portfolio.usecases.image.GetAllImagesUseCase;
import com.jonathan.portfolio.usecases.tool.GetAllToolsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProjectsUseCase {

    private final IProjectsRepository projectsRepository;
    private final GetAllImagesUseCase getAllImagesUseCase;
    private final GetAllToolsUseCase getAllToolsUseCase;

    @Autowired
    public GetAllProjectsUseCase(
            IProjectsRepository projectsRepository,
            GetAllImagesUseCase getAllImagesUseCase,
            GetAllToolsUseCase getAllToolsUseCase
    ) {
        this.projectsRepository = projectsRepository;
        this.getAllImagesUseCase = getAllImagesUseCase;
        this.getAllToolsUseCase = getAllToolsUseCase;
    }

    public List<ProjectDTO> get() {
        try {
            return projectsRepository.findAll().stream().map(project -> new ProjectDTO(
                    project.id(),
                    project.description(),
                    getAllImagesUseCase.get(project.imagesId()),
                    project.date(),
                    getAllToolsUseCase.get(project.toolsId())
            )).toList();
        } catch (Exception e) {
            throw new RuntimeException("The database transaction failed: " + e.getMessage());
        }
    }
}
