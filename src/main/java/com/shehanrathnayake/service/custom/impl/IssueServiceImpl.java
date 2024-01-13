package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.service.custom.IssueService;
import com.shehanrathnayake.to.IssueTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {
    @Override
    public IssueTO saveIssue(IssueTO issueTO) {
        return null;
    }

    @Override
    public void updateIssue(IssueTO issueTO) {

    }

    @Override
    public void deleteIssue(String issuedDateTime) {

    }

    @Override
    public IssueTO getIssueDetails(String issuedDateTime) {
        return null;
    }

    @Override
    public List<IssueTO> getAllIssues() {
        return null;
    }
}
