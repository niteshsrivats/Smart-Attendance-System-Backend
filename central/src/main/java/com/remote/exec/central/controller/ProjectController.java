package com.remote.exec.central.controller;


import com.remote.exec.central.models.entities.Project;
import com.remote.exec.central.models.entities.User;
import com.remote.exec.central.named.Endpoints;
import com.remote.exec.central.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(Endpoints.Projects.Id)
    public Project getProjectById(@PathVariable @NotBlank String userId, @PathVariable @NotBlank String projectId) {
        return projectService.getProjectById(projectId);
    }

    @PostMapping(Endpoints.Projects.Base)
    public Project addProject(@PathVariable @NotBlank String userId, @RequestBody @Valid @NotNull Project project) {
        return projectService.addProject(new User(userId), project);
    }
}
