package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, String> {
    List<Issue> findIssuesByUserId(int userId);
}
