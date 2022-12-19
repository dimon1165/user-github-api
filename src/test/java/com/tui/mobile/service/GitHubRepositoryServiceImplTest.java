package com.tui.mobile.service;

import com.tui.mobile.client.GitHubRepositoryClient;
import com.tui.mobile.client.GitHubUserClient;
import com.tui.mobile.dto.BranchDto;
import com.tui.mobile.dto.RepositoryDto;
import com.tui.mobile.mapper.BranchMapper;
import com.tui.mobile.mapper.RepositoryMapper;
import com.tui.mobile.model.GitHubBranch;
import com.tui.mobile.model.GitHubCommit;
import com.tui.mobile.model.GitHubRepository;
import com.tui.mobile.model.GitHubRepositoryOwner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GitHubRepositoryServiceImplTest {

    @Mock
    private GitHubUserClient gitHubUserClient;

    @Mock
    private GitHubRepositoryClient gitHubRepositoryClient;

    @Mock
    private RepositoryMapper repositoryMapper;

    @Mock
    private BranchMapper branchMapper;

    @Mock
    private GitHubBranchesServiceImpl gitHubBranchesService;

    @InjectMocks
    private GitHubRepositoryServiceImpl gitHubRepositoryService;

    @Test
    void testGetRepositoryInfoByUseName() {
        // given
        var owner = "test_user";
        var branchName = "test_branch";
        var repositoryName = "test_repo";
        var sha = "as234sd232343sdf";

        var gitHubRepositories = List.of(GitHubRepository.builder()
                .name(repositoryName)
                .owner(GitHubRepositoryOwner.builder()
                        .login(owner)
                        .build())
                .build());

        var gitHubBranches = List.of(GitHubBranch.builder()
                .name(branchName)
                .commit(GitHubCommit.builder()
                        .sha(sha)
                        .build())
                .build());

        var branchDtos = List.of(BranchDto.builder()
                .name(branchName)
                .lastCommitSha1(sha)
                .build());

        var repositoryDto = RepositoryDto.builder()
                .repositoryName(repositoryName)
                .ownerLogin(owner)
                .branches(List.of(BranchDto.builder()
                        .name(branchName)
                        .lastCommitSha1(sha)
                        .build()))
                .build();

        var expected = List.of(repositoryDto);

        when(gitHubUserClient.getRepositoriesByUserLogin(owner)).thenReturn(gitHubRepositories);
        when(gitHubBranchesService.getBranchesByOwnerAndRepositoryName(owner, repositoryName)).thenReturn(gitHubBranches);
        when(branchMapper.toBranchDto(anyList())).thenReturn(branchDtos);
        when(repositoryMapper.toRepositoryDto(any(), anyList())).thenReturn(repositoryDto);

        // when
        var actual = gitHubRepositoryService.getRepositoryInfoByUseName(owner);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
