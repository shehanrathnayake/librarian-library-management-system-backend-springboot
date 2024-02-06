package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.service.custom.IssueService;
import com.shehanrathnayake.service.util.BookTransformer;
import com.shehanrathnayake.service.util.EmployeeTransformer;
import com.shehanrathnayake.service.util.UserTransformer;
import com.shehanrathnayake.to.BookTO;
import com.shehanrathnayake.to.EmployeeTO;
import com.shehanrathnayake.to.IssueTO;
import com.shehanrathnayake.to.UserTO;
import com.shehanrathnayake.util.BookCategory;
import com.shehanrathnayake.util.IssueStatus;
import com.shehanrathnayake.util.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IssueServiceImplTest {

    @Autowired
    private EntityManager em;


    private final BookTransformer bookTransformer;
    private final EmployeeTransformer employeeTransformer;
    private final UserTransformer userTransformer;
    private final IssueService issueService;

    private BookTO bookTO;
    private BookTO bookTO2;
    private EmployeeTO employeeTO;
    private EmployeeTO employeeTO2;

    private UserTO userTO;
    private UserTO userTO2;

    @Autowired
    IssueServiceImplTest(BookTransformer bookTransformer, EmployeeTransformer employeeTransformer, UserTransformer userTransformer, IssueService issueService) {
        this.bookTransformer = bookTransformer;
        this.employeeTransformer = employeeTransformer;
        this.userTransformer = userTransformer;
        this.issueService = issueService;
    }

    @BeforeEach
    void setUp() {
        bookTO = new BookTO(
                "1234567891234",
                "Arts and Music",
                "A great masterpiece done by Author",
                "book/1",
                "Peter Alwis",
                BookCategory.ENTERTAINMENT,
                5,
                3
        );
        bookTO2 = new BookTO(
                "1234567891235",
                "Mark Zuckerburg",
                "A great masterpiece done by Author",
                "book/2",
                "Peter Perera",
                BookCategory.BIOGRAPHIES,
                7,
                3
        );
        employeeTO = new EmployeeTO(
                "Kelum Srimal",
                "Librarian",
                "Colombo",
                "0771223456",
                "123456"
        );

        employeeTO2 = new EmployeeTO(
                "Prabath Srimal",
                "Assistant Librarian",
                "Galle",
                "0771223457",
                "1234567"
        );
        userTO = new UserTO(
                "Sithum Perera",
                "email@gmail.com",
                "123456",
                "Matara",
                "0711313951",
                UserRole.MEMBER
        );
        userTO2 = new UserTO(
                "Jagath Fernando",
                "email2@gmail.com",
                "1234563",
                "Tangalle",
                "0711313985",
                UserRole.MEMBER
        );
        em.persist(bookTransformer.fromBookTO(bookTO));
        em.persist(bookTransformer.fromBookTO(bookTO2));
        em.persist(employeeTransformer.fromEmployeeTO(employeeTO));
        em.persist(employeeTransformer.fromEmployeeTO(employeeTO2));
        em.persist(userTransformer.fromUserTO(userTO));
        em.persist(userTransformer.fromUserTO(userTO2));

    }

    @Test
    void saveIssue() {
        IssueTO issueTO = new IssueTO(
                new Date(1705870901),
                IssueStatus.BORROWED,
                0,
                employeeTO.getId(),
                bookTO.getId(),
                userTO.getId()
        );
        IssueTO savedIssueTo = issueService.saveIssue(issueTO);

        assertEquals(savedIssueTo.getIssuedDate(), issueTO.getIssuedDate());
        assertEquals(savedIssueTo.getStatus(), issueTO.getStatus());
        assertEquals(savedIssueTo.getRenews(), issueTO.getRenews());
        assertEquals(savedIssueTo.getIssuedEmployee(), issueTO.getIssuedEmployee());
        assertEquals(savedIssueTo.getBookId(), issueTO.getBookId());
        assertEquals(savedIssueTo.getUserId(), issueTO.getUserId());
    }

    @Test
    void updateIssue() {
        IssueTO issueTO = new IssueTO(
                new Date(1705870901),
                IssueStatus.BORROWED,
                0,
                employeeTO.getId(),
                bookTO.getId(),
                userTO.getId()
        );
        IssueTO foundIssueTO = issueService.saveIssue(issueTO);

        foundIssueTO.setIssuedDate(new Date(1705870801));
        foundIssueTO.setStatus(IssueStatus.RENEWED);
        foundIssueTO.setRenews(1);
        foundIssueTO.setIssuedEmployee(employeeTO2.getId());
        foundIssueTO.setBookId(bookTO2.getId());
        foundIssueTO.setUserId(userTO2.getId());

        issueService.updateIssue(foundIssueTO);

        IssueTO updatedFoundIssueTO = em.find(IssueTO.class, foundIssueTO.getId());

        assertEquals(updatedFoundIssueTO.getIssuedDate(), foundIssueTO.getIssuedDate());
        assertEquals(updatedFoundIssueTO.getStatus(), foundIssueTO.getStatus());
        assertEquals(updatedFoundIssueTO.getRenews(), foundIssueTO.getRenews());
        assertEquals(updatedFoundIssueTO.getIssuedEmployee(), foundIssueTO.getIssuedEmployee());
        assertEquals(updatedFoundIssueTO.getBookId(), foundIssueTO.getBookId());
        assertEquals(updatedFoundIssueTO.getUserId(), foundIssueTO.getUserId());

    }

    @Test
    void deleteIssue() {
        IssueTO issueTO = new IssueTO(
                new Date(1705870901),
                IssueStatus.BORROWED,
                0,
                employeeTO.getId(),
                bookTO.getId(),
                userTO.getId()
        );
        IssueTO savedIssueTO = issueService.saveIssue(issueTO);
        IssueTO foundIssueTO = em.find(IssueTO.class, savedIssueTO);
        assertNotNull(foundIssueTO);
        assertEquals(savedIssueTO, foundIssueTO);

        issueService.deleteIssue(savedIssueTO.getId());
        foundIssueTO = em.find(IssueTO.class, savedIssueTO);
        assertNull(foundIssueTO);
    }

    @Test
    void getAllIssues() {
        for (int i = 0; i < 10; i++) {
            IssueTO issueTO = new IssueTO(
                    new Date(1705870901 + i),
                    IssueStatus.BORROWED,
                    0,
                    employeeTO.getId(),
                    bookTO.getId(),
                    userTO.getId()
            );
            issueService.saveIssue(issueTO);
        }
        assertEquals(issueService.getAllIssues(userTO.getId()).size(), 10);
    }
}