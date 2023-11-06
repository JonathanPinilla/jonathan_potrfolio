package com.jonathan.portfolio.usecases.project;

import com.jonathan.portfolio.dto.ProjectDTO;
import com.jonathan.portfolio.models.Project;
import com.jonathan.portfolio.repository.IProjectsRepository;
import com.jonathan.portfolio.usecases.image.GetAllImagesUseCase;
import com.jonathan.portfolio.usecases.tool.GetAllToolsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetAllProjectsUseCaseTest {

    IProjectsRepository projectsRepository = Mockito.mock(IProjectsRepository.class);
    GetAllProjectsUseCase getAllProjectsUseCase;
    GetAllToolsUseCase getAllToolsUseCase = Mockito.mock(GetAllToolsUseCase.class);
    GetAllImagesUseCase getAllImagesUseCase = Mockito.mock(GetAllImagesUseCase.class);

    @BeforeEach
    void setUp() {
        getAllProjectsUseCase = new GetAllProjectsUseCase(
                projectsRepository,
                getAllImagesUseCase,
                getAllToolsUseCase
        );
    }

    @Test
    void successfulScenario() {
        Project project1 = new Project(
                "id",
                "description",
                List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                LocalDate.now(),
                List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );
        Project project2 = new Project(
                "id2",
                "description2",
                List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                LocalDate.now(),
                List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        Mockito.when(projectsRepository.findAll()).thenReturn(List.of(project1, project2));
        Mockito.when(getAllImagesUseCase.get(Mockito.anyList())).thenReturn(List.of());
        Mockito.when(getAllToolsUseCase.get(Mockito.anyList())).thenReturn(List.of());

        List<ProjectDTO> projects = getAllProjectsUseCase.get();

        assertEquals(2, projects.size());
        assertEquals(project1.id(), projects.get(0).id());
        assertEquals(project2.description(), projects.get(1).description());
    }

    @Test
    void databaseErrorScenario() {
        Mockito.when(projectsRepository.findAll()).thenThrow(
                new RuntimeException("Database error")
        );

        assertThrows(RuntimeException.class, getAllProjectsUseCase::get);
    }
}