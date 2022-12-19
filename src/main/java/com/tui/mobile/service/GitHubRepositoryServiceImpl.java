package com.tui.mobile.service;

import com.tui.mobile.client.GitHubUserClient;
import com.tui.mobile.dto.RepositoryDto;
import com.tui.mobile.mapper.BranchMapper;
import com.tui.mobile.mapper.RepositoryMapper;
import com.tui.mobile.model.GitHubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GitHubRepositoryServiceImpl implements GitHubRepositoryService {
    private final GitHubUserClient gitHubUserClient;
    private final GitHubBranchesService gitHubBranchesService;
    private final RepositoryMapper repositoryMapper;
    private final BranchMapper branchMapper;

    @Value("${repository.fork.search.enable}")
    private boolean repositoryForkSearchEnable;

    @Override
    public List<RepositoryDto> getRepositoryInfoByUseName(String owner) {
        return gitHubUserClient.getRepositoriesByUserLogin(owner)
                .parallelStream()
                .filter(disableEnableFork())
                .map(repository -> repositoryMapper.toRepositoryDto(repository,
                        branchMapper.toBranchDto(
                                gitHubBranchesService.getBranchesByOwnerAndRepositoryName(owner, repository.getName()))))
                .collect(Collectors.toList());
    }

    private Predicate<GitHubRepository> disableEnableFork() {
        return repository -> repository.isFork() == repositoryForkSearchEnable;
    }
}
