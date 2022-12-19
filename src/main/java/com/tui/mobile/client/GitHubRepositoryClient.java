package com.tui.mobile.client;

import com.tui.mobile.model.GitHubBranch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient(name = "github-repository", url = "${client.github.url}/repos")
public interface GitHubRepositoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{owner}/{repositoryName}/branches")
    List<GitHubBranch> getBranchByRepositoryName(@PathVariable String owner, @PathVariable String repositoryName);
}
