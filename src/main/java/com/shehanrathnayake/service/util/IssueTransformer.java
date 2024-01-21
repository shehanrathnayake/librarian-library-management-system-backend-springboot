package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.BookPropertiesConverter;
import com.shehanrathnayake.converter.IssuePropertiesConverter;
import com.shehanrathnayake.converter.EmployeePropertiesConverter;
import com.shehanrathnayake.converter.UserPropertiesConverter;
import com.shehanrathnayake.entity.Book;
import com.shehanrathnayake.entity.Issue;
import com.shehanrathnayake.entity.Employee;
import com.shehanrathnayake.entity.User;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.BookRepository;
import com.shehanrathnayake.repository.EmployeeRepository;
import com.shehanrathnayake.repository.UserRepository;
import com.shehanrathnayake.to.IssueTO;
import com.shehanrathnayake.util.IssueStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class IssueTransformer {
    private final ModelMapper mapper;

    public IssueTransformer(ModelMapper mapper, IssuePropertiesConverter issuePropertiesConverter,
                            EmployeePropertiesConverter employeeConverter, EmployeeRepository employeeRepository,
                            BookPropertiesConverter bookConverter, BookRepository bookRepository,
                            UserPropertiesConverter userConverter, UserRepository userRepository) {
        this.mapper = mapper;

        /* Convert IssueStatus to String and wise versa */

        mapper.typeMap(IssueStatus.class, String.class)
                .setConverter(ctx -> ctx.getSource().getStatus());
        mapper.typeMap(String.class, IssueStatus.class)
                .setConverter(ctx -> issuePropertiesConverter.convert(ctx.getSource()));

        /* Convert Staff to String and wise versa */

        mapper.typeMap(Employee.class, String.class)
                .setConverter(ctx -> employeeConverter.covertToString(ctx.getSource().getId()));
        mapper.typeMap(String.class, Employee.class)
                .setConverter(ctx -> {
                    Optional<Employee> optEmployee = employeeRepository.findById(employeeConverter.convertIdToInt(ctx.getSource()));
                    if (optEmployee.isPresent()) return optEmployee.get();
                    else throw new AppException(404, "No staff Officer associate with the staff ID");
                });

        /* Convert Book to String and wise versa */

        mapper.typeMap(Book.class, String.class)
                .setConverter(ctx -> bookConverter.covertToString(ctx.getSource().getId()));

        mapper.typeMap(String.class, Book.class)
                .setConverter(ctx -> {
                    Optional<Book> optBook = bookRepository.findById(bookConverter.convertIdToInt(ctx.getSource()));
                    if (optBook.isPresent()) return optBook.get();
                    else throw new AppException(404, "No book is associated with the book ID");
                });

        /* Convert User to String and wise versa */

        mapper.typeMap(User.class, String.class)
                .setConverter(ctx -> userConverter.convertIdToString(ctx.getSource().getId()));
        mapper.typeMap(String.class, User.class)
                .setConverter(ctx -> {
                    Optional<User> optUser = userRepository.findById(userConverter.convertIdToInt(ctx.getSource()));
                    if (optUser.isPresent()) return  optUser.get();
                    else throw new AppException(404, "No user associated with the user ID");
                });

        /* Convert SQL date to String and wise versa */

        mapper.typeMap(Date.class, String.class)
                .setConverter(ctx -> ctx.getSource().toString());
        mapper.typeMap(String.class, Date.class)
                .setConverter(ctx -> Date.valueOf(ctx.getSource()));
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
