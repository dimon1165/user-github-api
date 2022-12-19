package com.tui.mobile.service;

import java.util.List;

public interface VCSRepository<T, ID> {

    List<T> getRepositoryInfoByUseName(ID id);
}
