package com.LibraryManagementSystem.repository;

import com.LibraryManagementSystem.models.LibraryBranch;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BranchRepository {
    private final Map<String, LibraryBranch> branches = new ConcurrentHashMap<>();

    public void save(LibraryBranch branch){
        branches.put(branch.getId(),branch);
    }

    public Optional<LibraryBranch> findById(String id){
        return Optional.ofNullable(branches.get(id));
    }

    public List<LibraryBranch> findAll(){
        return new ArrayList<>(branches.values());
    }

    public boolean delete(String id){
        return branches.remove(id) != null;
    }
}
