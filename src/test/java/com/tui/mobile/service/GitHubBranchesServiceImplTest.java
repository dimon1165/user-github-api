package com.tui.mobile.service;

import com.tui.mobile.client.GitHubRepositoryClient;
import com.tui.mobile.model.GitHubBranch;
import com.tui.mobile.model.GitHubCommit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GitHubBranchesServiceImplTest {

    @InjectMocks
    private GitHubBranchesServiceImpl gitHubBranchesService;
    @Mock
    private GitHubRepositoryClient gitHubRepositoryClient;

    @Test
    void testGetBranchesByOwnerAndRepositoryName() {
        // given
        var owner = "test_user";
        var branchName = "test_branch";
        var repositoryName = "test_repo";
        var sha = "as234sd232343sdf";
        var expected = List.of(GitHubBranch.builder()
                .name(branchName)
                .commit(GitHubCommit.builder()
                        .sha(sha)
                        .build())
                .build());

        when(gitHubRepositoryClient.getBranchByRepositoryName(owner, repositoryName)).thenReturn(expected);

        // when
        var actual = gitHubBranchesService.getBranchesByOwnerAndRepositoryName(owner, repositoryName);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);

    }
}
