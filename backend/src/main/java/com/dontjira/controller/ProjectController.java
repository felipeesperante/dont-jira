package com.dontjira.controller;

import com.dontjira.domain.Project;
import com.dontjira.service.ProjectService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody CreateProjectRequest request) {
        Project created = projectService.createProject(
                request.creatorUserId,
                request.name,
                request.description,
                request.plannedStartDate,
                request.plannedEndDate
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{projectId}/membership-requests")
    public ResponseEntity<Void> requestMembership(@PathVariable Long projectId, @RequestParam Long userId) {
        projectService.requestMembership(projectId, userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{projectId}/members")
    public ResponseEntity<Void> addMember(@PathVariable Long projectId, @RequestParam Long userId) {
        projectService.addMember(projectId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public static class CreateProjectRequest {
        public Long creatorUserId;
        @NotBlank public String name;
        @NotBlank public String description;
        public LocalDate plannedStartDate;
        public LocalDate plannedEndDate;
    }
}
