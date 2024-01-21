package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.converter.IssuePropertiesConverter;
import com.shehanrathnayake.converter.UserPropertiesConverter;
import com.shehanrathnayake.entity.Issue;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.IssueRepository;
import com.shehanrathnayake.repository.UserRepository;
import com.shehanrathnayake.service.custom.IssueService;
import com.shehanrathnayake.service.util.IssueTransformer;
import com.shehanrathnayake.service.util.UserTransformer;
import com.shehanrathnayake.to.IssueTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final IssueTransformer transformer;
    private final IssuePropertiesConverter issuePropsConverter;
    private final UserRepository userRepository;
    private final UserPropertiesConverter userPropsConverter;
    public IssueServiceImpl(IssueRepository issueRepository, IssueTransformer transformer, IssuePropertiesConverter issuePropsConverter, UserRepository userRepository, UserTransformer userTransformer, UserPropertiesConverter userPropsConverter) {
        this.issueRepository = issueRepository;
        this.transformer = transformer;
        this.issuePropsConverter = issuePropsConverter;
        this.userRepository = userRepository;
        this.userPropsConverter = userPropsConverter;
    }

    @Override
    public IssueTO saveIssue(IssueTO issueTO) {
        Issue issue = transformer.fromIssueTO(issueTO);
        Issue savedIssue = issueRepository.save(issue);
        return transformer.toIssueTO(savedIssue);
    }

    @Override
    public void updateIssue(IssueTO issueTO) {
        Issue issue = transformer.fromIssueTO(issueTO);
        issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(String issueId) {
        Issue issue = issueRepository.findById(issuePropsConverter.convertIdToInt(issueId)).orElseThrow(() -> new AppException(404, "No issue associated with the issue date and time"));
        issueRepository.delete(issue);
    }

//    @Override
//    public IssueTO getIssueDetails(String issuedDateTime) {
//        Optional<Issue> optIssue = issueRepository.findById(issuedDateTime);
//        if (optIssue.isEmpty()) throw new  AppException(404, "No issue found");
//        return transformer.toIssueTO(optIssue.get());
//    }

    @Override
    public List<IssueTO> getAllIssues(String userId) {
        int userIdAsInt = userPropsConverter.convertIdToInt(userId);
        if (userRepository.existsById(userIdAsInt)) {
            List<Issue> issueList = issueRepository.findIssuesByUserId(userIdAsInt);
            return transformer.toIssueTOList(issueList);
        } else {
            throw new AppException(404, "No user associated with the user ID");
        }
    }
}
