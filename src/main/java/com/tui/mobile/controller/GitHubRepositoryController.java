package com.tui.mobile.controller;

import com.tui.mobile.dto.RepositoryDto;
import com.tui.mobile.service.GitHubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repository")
public class GitHubRepositoryController {

    private final GitHubRepositoryService gitHubRepositoryService;

    @GetMapping(value = "/user/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RepositoryDto> getUserGitHubRepoInfo(@PathVariable String login) {
        return gitHubRepositoryService.getRepositoryInfoByUseName(login);
    }
}
