package com.tui.mobile.service;

import com.tui.mobile.dto.RepositoryDto;

import java.util.List;

public interface GitHubRepositoryService extends VCSRepository<RepositoryDto, String> {
    @Override
    List<RepositoryDto> getRepositoryInfoByUseName(String login);
}
