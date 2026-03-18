package com.dontjira.service;

import com.dontjira.domain.Project;

import java.time.LocalDate;

public interface ProjectService {
    Project createProject(Long creatorUserId, String name, String description, LocalDate plannedStartDate, LocalDate plannedEndDate);
    void requestMembership(Long projectId, Long userId);
    void addMember(Long projectId, Long userId);
}
