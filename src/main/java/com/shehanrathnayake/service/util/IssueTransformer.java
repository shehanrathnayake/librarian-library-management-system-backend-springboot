package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.IssueStatusConverter;
import com.shehanrathnayake.entity.Issue;
import com.shehanrathnayake.to.IssueTO;
import com.shehanrathnayake.util.IssueStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IssueTransformer {
    private final ModelMapper mapper;
    private final IssueStatusConverter issueStatusConverter;

    public IssueTransformer(ModelMapper mapper, IssueStatusConverter issueStatusConverter) {
        this.mapper = mapper;
        this.issueStatusConverter = issueStatusConverter;

        mapper.typeMap(IssueStatus.class, String.class)
                .setConverter(ctx -> ctx.getSource().getStatus());
        mapper.typeMap(String.class, IssueStatus.class)
                .setConverter(ctx -> issueStatusConverter.convert(ctx.getSource()));
    }

    public Issue fromIssueTO(IssueTO issueTO) {
        return mapper.map(issueTO, Issue.class);
    }

    public IssueTO toIssueTO(Issue issue) {
        return mapper.map(issue, IssueTO.class);
    }

    public List<IssueTO> toIssueTOList(List<Issue> issueList) {
        return issueList.stream().map(this::toIssueTO).collect(Collectors.toList());
    }
}
