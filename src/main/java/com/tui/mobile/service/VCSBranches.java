package com.tui.mobile.service;

import java.util.List;

public interface VCSBranches<T, ID> {

    List<T> getBranchesByOwnerAndRepositoryName(String owner, ID id);
}
