package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.converter.IssuePropertiesConverter;
import com.shehanrathnayake.converter.IssueReturnPropertiesConverter;
import com.shehanrathnayake.entity.Issue;
import com.shehanrathnayake.entity.IssueReturn;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.IssueRepository;
import com.shehanrathnayake.repository.IssueReturnRepository;
import com.shehanrathnayake.service.custom.IssueReturnService;
import com.shehanrathnayake.service.util.IssueReturnTransformer;
import com.shehanrathnayake.to.IssueReturnTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class IssueReturnServiceImpl implements IssueReturnService {

    private final IssueReturnTransformer issueReturnTransformer;
    private final IssueReturnRepository issueReturnRepository;
    private final IssueReturnPropertiesConverter issueReturnPropsConverter;
    private final IssueRepository issueRepository;
    private final IssuePropertiesConverter issuePropsConverter;

    public IssueReturnServiceImpl(IssueReturnTransformer issueReturnTransformer, IssueReturnRepository issueReturnRepository, IssueReturnPropertiesConverter issueReturnPropsConverter,
                                  IssueRepository issueRepository, IssuePropertiesConverter issuePropsConverter) {
        this.issueReturnTransformer = issueReturnTransformer;
        this.issueReturnRepository = issueReturnRepository;
        this.issueReturnPropsConverter = issueReturnPropsConverter;
        this.issueRepository = issueRepository;
        this.issuePropsConverter = issuePropsConverter;
    }

    @Override
    public IssueReturnTO saveIssueReturn(IssueReturnTO issueReturnTO) {
        IssueReturn issueReturn = issueReturnTransformer.fromIssueReturnTO(issueReturnTO);
        return issueReturnTransformer.toIssueReturnTO(issueReturnRepository.save(issueReturn));
    }

    @Override
    public void updateIssueReturn(IssueReturnTO issueReturnTO) {
        issueReturnRepository.findById(issueReturnPropsConverter.convertIdToInt(issueReturnTO.getId())).orElseThrow(() -> new AppException(404, "There is no return record associate with the return ID"));
        IssueReturn issueReturn = issueReturnTransformer.fromIssueReturnTO(issueReturnTO);
        issueReturnRepository.save(issueReturn);
    }

    @Override
    public void deleteIssueReturn(String issueReturnId) {
        IssueReturn issueReturn = issueReturnRepository.findById(issueReturnPropsConverter.convertIdToInt(issueReturnId)).orElseThrow(() -> new AppException(404, "No return record found associated with the return ID"));
        issueReturnRepository.delete(issueReturn);
    }

    @Override
    public IssueReturnTO getIssueReturnDetails(String issueId) {
        Issue issue = issueRepository.findById(issuePropsConverter.convertIdToInt(issueId)).orElseThrow(() -> new AppException(404, "Issue not found"));
//        IssueReturn issueReturn = issueReturnRepository.findIssueReturnByIssueId(issue);
        IssueReturn issueReturn = issueReturnRepository.findIssueReturnByIssueId(issuePropsConverter.convertIdToInt(issueId));
        return (issueReturn != null) ? issueReturnTransformer.toIssueReturnTO(issueReturn) :new IssueReturnTO();
    }
}
