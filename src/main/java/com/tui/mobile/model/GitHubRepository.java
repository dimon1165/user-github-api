package com.tui.mobile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GitHubRepository {

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private GitHubRepositoryOwner owner;

    private boolean fork;
}
