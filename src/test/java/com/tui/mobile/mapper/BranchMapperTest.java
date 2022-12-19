package com.tui.mobile.mapper;

import com.tui.mobile.dto.BranchDto;
import com.tui.mobile.model.GitHubBranch;
import com.tui.mobile.model.GitHubCommit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BranchMapperTest {


    @Autowired
    private BranchMapper branchMapper;

    @Test
    void testGitHubBranchIsMappedToBranchDto() {
        //given
        var branchName = "main";
        var lastCommitSha = "as234sd232343sdf";
        var expected = buildBranchDto(branchName, lastCommitSha);

        //when
        var actual = branchMapper.toBranchDto(buildGitHubBranch(branchName, lastCommitSha));

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGitHubBranchesAreMappedToBranchDtos() {
        //given
        var branchName = "main";
        var lastCommitSha = "as234sd232343sdf";
        var expected = List.of(buildBranchDto(branchName, lastCommitSha));

        //when
        var actual = branchMapper.toBranchDto(List.of(buildGitHubBranch(branchName, lastCommitSha)));

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private BranchDto buildBranchDto(String branchName, String lastCommitSha) {
        return BranchDto.builder()
                .name(branchName)
                .lastCommitSha1(lastCommitSha)
                .build();
    }

    private GitHubBranch buildGitHubBranch(String branchName, String lastCommitSha) {
        return GitHubBranch.builder()
                .name(branchName)
                .commit(GitHubCommit.builder()
                        .sha(lastCommitSha)
                        .build())
                .build();
    }
}
