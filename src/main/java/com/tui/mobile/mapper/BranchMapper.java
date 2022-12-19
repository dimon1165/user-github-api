package com.tui.mobile.mapper;

import com.tui.mobile.dto.BranchDto;
import com.tui.mobile.model.GitHubBranch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    List<BranchDto> toBranchDto(List<GitHubBranch> branches);

    @Mapping(source = "branch.commit.sha", target = "lastCommitSha1")
    BranchDto toBranchDto(GitHubBranch branch);
}
