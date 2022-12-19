package com.tui.mobile.service;

import com.tui.mobile.client.GitHubRepositoryClient;
import com.tui.mobile.model.GitHubBranch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubBranchesServiceImpl implements GitHubBranchesService {

    private final GitHubRepositoryClient gitHubRepositoryClient;

    @Override
    public List<GitHubBranch> getBranchesByOwnerAndRepositoryName(String owner, String repositoryName) {
        return gitHubRepositoryClient.getBranchByRepositoryName(owner, repositoryName);
    }
}
