package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.IssueReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IssueReturnRepository extends JpaRepository<IssueReturn, Integer> {
    @Query(value = "SELECT * FROM issue_return WHERE issue_id = ?", nativeQuery = true)
    IssueReturn findIssueReturnByIssueId(Integer issueId);
}
