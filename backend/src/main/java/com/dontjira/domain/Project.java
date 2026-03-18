package com.dontjira.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 3000)
    private String description;

    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by_user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_project_created_by_user"))
    private UserAccount createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getPlannedStartDate() { return plannedStartDate; }
    public void setPlannedStartDate(LocalDate plannedStartDate) { this.plannedStartDate = plannedStartDate; }
    public LocalDate getPlannedEndDate() { return plannedEndDate; }
    public void setPlannedEndDate(LocalDate plannedEndDate) { this.plannedEndDate = plannedEndDate; }
    public UserAccount getCreatedBy() { return createdBy; }
    public void setCreatedBy(UserAccount createdBy) { this.createdBy = createdBy; }
}
