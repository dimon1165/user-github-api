package com.tui.mobile.client;

import com.tui.mobile.model.GitHubRepository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient(name = "github-user", url = "${client.github.url}/users")
public interface GitHubUserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{username}/repos")
    List<GitHubRepository> getRepositoriesByUserLogin(@PathVariable String username);
}
