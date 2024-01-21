package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.to.IssueReturnTO;

public interface IssueReturnService extends SuperService {
    IssueReturnTO saveIssueReturn(IssueReturnTO issueReturnTO);
    void updateIssueReturn(IssueReturnTO issueReturnTO);
    void deleteIssueReturn(String issueReturnId);
    IssueReturnTO getIssueReturnDetails(String issueId);
}
