package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.IssuePropertiesConverter;
import com.shehanrathnayake.converter.IssueReturnPropertiesConverter;
import com.shehanrathnayake.converter.StaffPropertiesConverter;
import com.shehanrathnayake.entity.Issue;
import com.shehanrathnayake.entity.IssueReturn;
import com.shehanrathnayake.entity.Staff;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.IssueRepository;
import com.shehanrathnayake.repository.StaffRepository;
import com.shehanrathnayake.to.IssueReturnTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IssueReturnTransformer {
    private final ModelMapper mapper;

    public IssueReturnTransformer(ModelMapper mapper, IssueReturnPropertiesConverter converter, IssueRepository issueRepository,
                                  IssuePropertiesConverter issueConverter,
                                  StaffRepository staffRepository, StaffPropertiesConverter staffConverter) {
        this.mapper = mapper;

        /* Convert String to Integer and wise versa */

        mapper.typeMap(String.class, Integer.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? converter.convertIdToInt(ctx.getSource()) : null);
        mapper.typeMap(Integer.class, String.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? converter.covertToString(ctx.getSource()) : null);

        /* Convert String to Issue and wise versa */

        mapper.typeMap(String.class, Issue.class)
                .setConverter(ctx -> {
                    Optional<Issue> optIssue = issueRepository.findById(issueConverter.convertIdToInt(ctx.getSource()));
                    if (optIssue.isPresent()) return optIssue.get();
                    else throw new AppException(404, "Invalid issue ID");
                }) ;

        mapper.typeMap(Issue.class, String.class)
                .setConverter(ctx -> issueConverter.covertToString(ctx.getSource().getId()));

        /* Convert String to Staff and wise versa */

        mapper.typeMap(String.class, Staff.class)
                .setConverter(ctx -> {
                    Optional<Staff> optStaff = staffRepository.findById(staffConverter.convertIdToInt(ctx.getSource()));
                    if (optStaff.isPresent()) return optStaff.get();
                    else throw new AppException(404, "No staff officer associated with the ID");
                });

        mapper.typeMap(Staff.class, String.class)
                .setConverter(ctx -> staffConverter.covertToString(ctx.getSource().getId()));


    }
    public IssueReturn fromIssueReturnTO(IssueReturnTO issueReturnTO) {
        return mapper.map(issueReturnTO, IssueReturn.class);
    }

    public IssueReturnTO toIssueReturnTO(IssueReturn issueReturn) {
        return mapper.map(issueReturn, IssueReturnTO.class);
    }

    public List<IssueReturnTO> toIssueReturnTOList(List<IssueReturn> issueReturnList) {
        return issueReturnList.stream().map(this::toIssueReturnTO).collect(Collectors.toList());
    }
}
