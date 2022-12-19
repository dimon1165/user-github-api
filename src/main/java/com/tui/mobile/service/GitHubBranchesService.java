package com.tui.mobile.service;

import com.tui.mobile.model.GitHubBranch;

import java.util.List;

public interface GitHubBranchesService extends VCSBranches<GitHubBranch, String> {

    @Override
    List<GitHubBranch> getBranchesByOwnerAndRepositoryName(String owner, String repositoryName);
}
