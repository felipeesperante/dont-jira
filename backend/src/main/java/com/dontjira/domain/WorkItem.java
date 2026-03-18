package com.dontjira.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_item")
public class WorkItem {
    public enum Type { EPIC, TASK, SUBTASK }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_work_item_project"))
    private Project project;

    @ManyToOne
    @JoinColumn(name = "parent_item_id",
            foreignKey = @ForeignKey(name = "fk_work_item_parent"))
    private WorkItem parent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private String title;

    @Column(length = 3000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public WorkItem getParent() { return parent; }
    public void setParent(WorkItem parent) { this.parent = parent; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
