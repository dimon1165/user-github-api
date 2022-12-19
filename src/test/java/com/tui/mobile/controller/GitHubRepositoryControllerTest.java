package com.tui.mobile.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.mobile.dto.BranchDto;
import com.tui.mobile.dto.RepositoryDto;
import com.tui.mobile.service.GitHubRepositoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GitHubRepositoryController.class)
public class GitHubRepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GitHubRepositoryService gitHubRepositoryService;

    @Test
    void testGetUserGitHubRepositoryInfo() throws Exception {
        // given
        var login = "test_user";
        var repositoryName = "test_repo";
        var branchName = "main";
        var lastCommitSha = "as234sd232343sdf";
        var expected = List.of(RepositoryDto.builder()
                .repositoryName(repositoryName)
                .ownerLogin(login)
                .branches(List.of(BranchDto.builder()
                        .name(branchName)
                        .lastCommitSha1(lastCommitSha)
                        .build()))
                .build());

        when(gitHubRepositoryService.getRepositoryInfoByUseName(anyString())).thenReturn(expected);

        // when
        var response = mockMvc.perform(get("/repository/user/{login}", login)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var actual = objectMapper.readValue(response, new TypeReference<List<RepositoryDto>>() {
        });

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
