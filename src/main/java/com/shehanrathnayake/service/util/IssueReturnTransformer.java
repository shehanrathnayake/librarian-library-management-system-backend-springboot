package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.IssuePropertiesConverter;
import com.shehanrathnayake.converter.IssueReturnPropertiesConverter;
import com.shehanrathnayake.converter.EmployeePropertiesConverter;
import com.shehanrathnayake.entity.Issue;
import com.shehanrathnayake.entity.IssueReturn;
import com.shehanrathnayake.entity.Employee;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.IssueRepository;
import com.shehanrathnayake.repository.EmployeeRepository;
import com.shehanrathnayake.to.IssueReturnTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
public class IssueReturnTransformer {
    private final ModelMapper mapper;

    public IssueReturnTransformer(ModelMapper mapper, IssueReturnPropertiesConverter converter, IssueRepository issueRepository,
                                  IssuePropertiesConverter issueConverter,
                                  EmployeeRepository employeeRepository, EmployeePropertiesConverter employeeConverter) {
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

        mapper.typeMap(String.class, Employee.class)
                .setConverter(ctx -> {
                    Optional<Employee> optEmployee = employeeRepository.findById(employeeConverter.convertIdToInt(ctx.getSource()));
                    if (optEmployee.isPresent()) return optEmployee.get();
                    else throw new AppException(404, "No staff officer associated with the ID");
                });

        mapper.typeMap(Employee.class, String.class)
                .setConverter(ctx -> employeeConverter.covertToString(ctx.getSource().getId()));

        /* Covert SQL date to string and wise versa */

        mapper.typeMap(Date.class, String.class)
                .setConverter(ctx -> ctx.getSource().toString());
        mapper.typeMap(String.class, Date.class)
                .setConverter(ctx -> Date.valueOf(ctx.getSource()));

    }
    public IssueReturn fromIssueReturnTO(IssueReturnTO issueReturnTO) {
        return mapper.map(issueReturnTO, IssueReturn.class);
    }

    public IssueReturnTO toIssueReturnTO(IssueReturn issueReturn) {
        return mapper.map(issueReturn, IssueReturnTO.class);
    }
}
