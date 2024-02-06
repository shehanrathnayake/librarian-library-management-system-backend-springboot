package com.shehanrathnayake.to;

import com.shehanrathnayake.util.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class IssueTO implements Serializable {
    @Null(message = "Issue ID should be empty")
    private String id;
    @NotNull(message = "Issue date cannot be null")
    private Date issuedDate;
    @NotNull(message = "Status cannot be empty")
    private IssueStatus status;
    private Integer renews;
    @Pattern(regexp = "^E\\d{6}$")
    private String issuedEmployee;
    @NotBlank(message = "Book ID cannot be empty")
    @Pattern(regexp = "^B\\d{6}$", message = "Invalid book Id")
    private String bookId;
    @NotBlank(message = "User ID cannot be empty")
    @Pattern(regexp = "^U\\d{6}$", message = "Invalid user Id")
    private String userId;

    public IssueTO(Date issuedDate, IssueStatus status, Integer renews, String issuedEmployee, String bookId, String userId) {
        this.issuedDate = issuedDate;
        this.status = status;
        this.renews = renews;
        this.issuedEmployee = issuedEmployee;
        this.bookId = bookId;
        this.userId = userId;
    }
}
