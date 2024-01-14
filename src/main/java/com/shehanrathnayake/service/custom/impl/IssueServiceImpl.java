package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.entity.Issue;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.IssueRepository;
import com.shehanrathnayake.service.custom.IssueService;
import com.shehanrathnayake.service.util.IssueTransformer;
import com.shehanrathnayake.to.IssueTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {
    private final IssueRepository repository;
    private final IssueTransformer transformer;

    public IssueServiceImpl(IssueRepository repository, IssueTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @Override
    public IssueTO saveIssue(IssueTO issueTO) {
        Issue issue = transformer.fromIssueTO(issueTO);
        Issue savedIssue = repository.save(issue);
        return transformer.toIssueTO(savedIssue);
    }

    @Override
    public void updateIssue(IssueTO issueTO) {
        repository.findById(issueTO.getIssuedDateTime()).orElseThrow(() -> new AppException(404, "No issue associated with the issued date and time"));
        Issue issue = transformer.fromIssueTO(issueTO);
        repository.save(issue);
    }

    @Override
    public void deleteIssue(String issuedDateTime) {
        Issue issue = repository.findById(issuedDateTime).orElseThrow(() -> new AppException(404, "No issue associated with the issue date and time"));
        repository.delete(issue);
    }

    @Override
    public IssueTO getIssueDetails(String issuedDateTime) {
        Optional<Issue> optIssue = repository.findById(issuedDateTime);
        if (optIssue.isEmpty()) throw new  AppException(404, "No issue found");
        return transformer.toIssueTO(optIssue.get());
    }

    @Override
    public List<IssueTO> getAllIssues() {
        List<Issue> issueList = repository.findAll();
        return transformer.toIssueTOList(issueList);
    }
}
