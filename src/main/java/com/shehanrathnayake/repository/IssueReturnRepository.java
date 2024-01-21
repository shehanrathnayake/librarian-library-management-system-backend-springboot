package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.IssueReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueReturnRepository extends JpaRepository<IssueReturn, Integer> {
    Optional<IssueReturn> findIssueReturnByIssueId(Integer issueId);
}
