package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.to.IssueTO;

import java.util.List;

public interface IssueService extends SuperService {
    IssueTO saveIssue(IssueTO issueTO);
    void updateIssue(IssueTO issueTO);
    void deleteIssue(String issueId);
//    IssueTO getIssueDetails(String issuedDateTime);
    List<IssueTO> getAllIssues(String userId);
}
