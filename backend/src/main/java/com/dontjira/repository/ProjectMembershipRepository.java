package com.dontjira.repository;

import com.dontjira.domain.ProjectMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectMembershipRepository extends JpaRepository<ProjectMembership, Long> {
    Optional<ProjectMembership> findByProjectIdAndUserId(Long projectId, Long userId);
}
