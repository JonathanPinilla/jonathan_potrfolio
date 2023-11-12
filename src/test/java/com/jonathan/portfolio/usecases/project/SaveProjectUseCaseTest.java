package com.jonathan.portfolio.usecases.project;

import com.jonathan.portfolio.dto.ProjectDTO;
import com.jonathan.portfolio.models.Image;
import com.jonathan.portfolio.models.Project;
import com.jonathan.portfolio.models.Tool;
import com.jonathan.portfolio.repository.IProjectsRepository;
import com.jonathan.portfolio.usecases.image.SaveImagesUseCase;
import com.jonathan.portfolio.usecases.tool.SaveToolUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveProjectUseCaseTest {

    private IProjectsRepository projectsRepository = Mockito.mock(IProjectsRepository.class);
    private SaveToolUseCase saveToolUseCase = Mockito.mock(SaveToolUseCase.class);
    private SaveImagesUseCase saveImagesUseCase = Mockito.mock(SaveImagesUseCase.class);

    private SaveProjectUseCase saveProjectUseCase;

    @BeforeEach
    void setUp() {
        saveProjectUseCase = new SaveProjectUseCase(projectsRepository, saveImagesUseCase, saveToolUseCase);
    }

    @Test
    void successfulScenario() {

        Tool tool = new Tool("name", 2);

        Image image = new Image("url", "someURl", "alt");

        ProjectDTO projectDTO = new ProjectDTO(
                "description",
                List.of(image),
                LocalDate.now(),
                List.of(tool)
        );

        Project project = new Project(
                projectDTO.id(),
                "description",
                List.of(image.id()),
                LocalDate.now(),
                List.of(tool.id())
        );

        Mockito.when(projectsRepository.save(project)).thenReturn(project);

        Project savedProject = projectsRepository.save(project);

        Assertions.assertEquals(projectDTO.id(), savedProject.id());
        Assertions.assertEquals(projectDTO.description(), savedProject.description());
        Assertions.assertEquals(projectDTO.images().get(0).id(), savedProject.imagesId().get(0));
        Assertions.assertEquals(projectDTO.date(), savedProject.date());
        Assertions.assertEquals(projectDTO.tools().get(0).id(), savedProject.toolsId().get(0));

    }

    @Test
    public void databaseErrorScenario() {

        Tool tool = new Tool("name", 2);

        Image image = new Image("url", "someURl", "alt");

        ProjectDTO projectDTO = new ProjectDTO(
                "description",
                List.of(image),
                LocalDate.now(),
                List.of(tool)
        );

        Project project = new Project(
                projectDTO.id(),
                "description",
                List.of(image.id()),
                LocalDate.now(),
                List.of(tool.id())
        );
        Mockito.when(saveImagesUseCase.save(projectDTO.images())).thenReturn(projectDTO.images());
        Mockito.when(saveToolUseCase.save(projectDTO.tools())).thenReturn(projectDTO.tools());
        Mockito.when(projectsRepository.save(project)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> saveProjectUseCase.save(projectDTO));
    }
}