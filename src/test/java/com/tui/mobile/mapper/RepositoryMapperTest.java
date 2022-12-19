package com.tui.mobile.mapper;

import com.tui.mobile.dto.BranchDto;
import com.tui.mobile.dto.RepositoryDto;
import com.tui.mobile.model.GitHubRepository;
import com.tui.mobile.model.GitHubRepositoryOwner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RepositoryMapperTest {

    @Autowired
    private RepositoryMapper repositoryMapper;

    @Test
    void testGitHubRepositoryAndBranchDtosAreMappedToRepositoryDto() {
        // given
        var repositoryName = "test_repo";
        var userLogin = "test_user";

        var gitHubRepository = GitHubRepository.builder()
                .name(repositoryName)
                .owner(GitHubRepositoryOwner.builder()
                        .login(userLogin)
                        .build())
                .build();

        var branchDtos = List.of(BranchDto.builder().build());

        var expected = RepositoryDto.builder()
                .repositoryName(repositoryName)
                .ownerLogin(userLogin)
                .branches(branchDtos)
                .build();

        // when
        var actual = repositoryMapper.toRepositoryDto(gitHubRepository, branchDtos);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
