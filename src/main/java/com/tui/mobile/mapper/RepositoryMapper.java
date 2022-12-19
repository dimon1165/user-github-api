package com.tui.mobile.mapper;

import com.tui.mobile.dto.BranchDto;
import com.tui.mobile.dto.RepositoryDto;
import com.tui.mobile.model.GitHubRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {

    @Mapping(source = "branches", target = "branches")
    @Mapping(source = "repository.name", target = "repositoryName")
    @Mapping(source = "repository.owner.login", target = "ownerLogin")
    RepositoryDto toRepositoryDto(GitHubRepository repository, List<BranchDto> branches);
}
