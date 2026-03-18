package com.dontjira.service.impl;

import com.dontjira.domain.Project;
import com.dontjira.domain.ProjectMembership;
import com.dontjira.domain.UserAccount;
import com.dontjira.exception.BusinessRuleException;
import com.dontjira.repository.ProjectMembershipRepository;
import com.dontjira.repository.ProjectRepository;
import com.dontjira.repository.UserAccountRepository;
import com.dontjira.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserAccountRepository userAccountRepository;
    private final ProjectMembershipRepository membershipRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserAccountRepository userAccountRepository,
                              ProjectMembershipRepository membershipRepository) {
        this.projectRepository = projectRepository;
        this.userAccountRepository = userAccountRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    @Transactional
    public Project createProject(Long creatorUserId, String name, String description, LocalDate plannedStartDate, LocalDate plannedEndDate) {
        if (name == null || name.isBlank()) throw new BusinessRuleException("Nome do projeto é obrigatório");
        if (description == null || description.isBlank()) throw new BusinessRuleException("Descrição do projeto é obrigatória");
        if (plannedStartDate != null && plannedEndDate != null && plannedEndDate.isBefore(plannedStartDate)) {
            throw new BusinessRuleException("Data de fim não pode ser anterior à data de início");
        }

        UserAccount creator = userAccountRepository.findById(creatorUserId)
                .orElseThrow(() -> new BusinessRuleException("Usuário criador não encontrado"));

        Project project = new Project();
        project.setName(name.trim());
        project.setDescription(description.trim());
        project.setPlannedStartDate(plannedStartDate);
        project.setPlannedEndDate(plannedEndDate);
        project.setCreatedBy(creator);

        Project saved = projectRepository.save(project);

        ProjectMembership ownerMembership = new ProjectMembership();
        ownerMembership.setProject(saved);
        ownerMembership.setUser(creator);
        ownerMembership.setStatus(ProjectMembership.Status.APPROVED);
        membershipRepository.save(ownerMembership);

        return saved;
    }

    @Override
    @Transactional
    public void requestMembership(Long projectId, Long userId) {
        if (membershipRepository.findByProjectIdAndUserId(projectId, userId).isPresent()) {
            throw new BusinessRuleException("Usuário já possui vínculo com o projeto");
        }

        ProjectMembership membership = new ProjectMembership();
        membership.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new BusinessRuleException("Projeto não encontrado")));
        membership.setUser(userAccountRepository.findById(userId)
                .orElseThrow(() -> new BusinessRuleException("Usuário não encontrado")));
        membership.setStatus(ProjectMembership.Status.REQUESTED);
        membershipRepository.save(membership);
    }

    @Override
    @Transactional
    public void addMember(Long projectId, Long userId) {
        if (membershipRepository.findByProjectIdAndUserId(projectId, userId).isPresent()) {
            throw new BusinessRuleException("Usuário já possui vínculo com o projeto");
        }

        ProjectMembership membership = new ProjectMembership();
        membership.setProject(projectRepository.findById(projectId)
                .orElseThrow(() -> new BusinessRuleException("Projeto não encontrado")));
        membership.setUser(userAccountRepository.findById(userId)
                .orElseThrow(() -> new BusinessRuleException("Usuário não encontrado")));
        membership.setStatus(ProjectMembership.Status.APPROVED);
        membershipRepository.save(membership);
    }
}
