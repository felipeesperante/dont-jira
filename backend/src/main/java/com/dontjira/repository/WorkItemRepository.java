package com.dontjira.repository;

import com.dontjira.domain.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {
    List<WorkItem> findByProjectId(Long projectId);
}
