package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.converter.IssueReturnPropertiesConverter;
import com.shehanrathnayake.entity.IssueReturn;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.IssueReturnRepository;
import com.shehanrathnayake.service.custom.IssueReturnService;
import com.shehanrathnayake.service.util.IssueReturnTransformer;
import com.shehanrathnayake.to.IssueReturnTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class IssueReturnServiceImpl implements IssueReturnService {

    private final IssueReturnTransformer issueReturnTransformer;
    private final IssueReturnRepository issueReturnRepository;
    private final IssueReturnPropertiesConverter issueReturnPropsConverter;

    public IssueReturnServiceImpl(IssueReturnTransformer issueReturnTransformer, IssueReturnRepository issueReturnRepository, IssueReturnPropertiesConverter issueReturnPropsConverter) {
        this.issueReturnTransformer = issueReturnTransformer;
        this.issueReturnRepository = issueReturnRepository;
        this.issueReturnPropsConverter = issueReturnPropsConverter;
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
        Optional<IssueReturn> optIssueReturn = issueReturnRepository.findIssueReturnByIssueId(issueReturnPropsConverter.convertIdToInt(issueId));
        return optIssueReturn.map(issueReturnTransformer::toIssueReturnTO).orElse(null);
    }
}
