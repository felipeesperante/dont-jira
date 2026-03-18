package com.dontjira.service;

import com.dontjira.domain.Project;
import com.dontjira.domain.ProjectMembership;
import com.dontjira.domain.UserAccount;
import com.dontjira.exception.BusinessRuleException;
import com.dontjira.repository.ProjectMembershipRepository;
import com.dontjira.repository.ProjectRepository;
import com.dontjira.repository.UserAccountRepository;
import com.dontjira.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {
    private ProjectRepository projectRepository;
    private UserAccountRepository userAccountRepository;
    private ProjectMembershipRepository membershipRepository;
    private ProjectServiceImpl service;

    @BeforeEach
    void setup() {
        projectRepository = mock(ProjectRepository.class);
        userAccountRepository = mock(UserAccountRepository.class);
        membershipRepository = mock(ProjectMembershipRepository.class);
        service = new ProjectServiceImpl(projectRepository, userAccountRepository, membershipRepository);
    }

    @Test
    void shouldRejectInvalidDateRange() {
        assertThrows(BusinessRuleException.class,
                () -> service.createProject(1L, "X", "Y", LocalDate.now(), LocalDate.now().minusDays(1)));
    }

    @Test
    void shouldCreateMembershipRequest() {
        UserAccount user = new UserAccount();
        Project project = new Project();
        when(membershipRepository.findByProjectIdAndUserId(1L, 2L)).thenReturn(Optional.empty());
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userAccountRepository.findById(2L)).thenReturn(Optional.of(user));

        service.requestMembership(1L, 2L);

        verify(membershipRepository).save(any(ProjectMembership.class));
    }
}
