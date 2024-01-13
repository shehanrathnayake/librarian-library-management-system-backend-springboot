package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, String> {
}
