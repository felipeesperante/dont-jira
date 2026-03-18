package com.dontjira.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_membership",
       uniqueConstraints = @UniqueConstraint(name = "uk_membership_project_user", columnNames = {"project_id", "user_id"}))
public class ProjectMembership {
    public enum Status { REQUESTED, APPROVED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_membership_project"))
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_membership_user"))
    private UserAccount user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public UserAccount getUser() { return user; }
    public void setUser(UserAccount user) { this.user = user; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
