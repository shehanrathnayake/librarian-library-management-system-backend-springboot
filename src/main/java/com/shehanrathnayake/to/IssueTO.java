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
    private String id;
    @Null(message = "Issue ID should be empty")
    private Date issuedDate;
    @NotNull(message = "Status cannot be empty")
    private IssueStatus status;
    @Pattern(regexp = "^\\d+$", message = "Only integer values are acceptable")
    private Integer renews;
    @NotBlank(message = "Issued officer cannot be empty")
    @Pattern(regexp = "^S\\d{6}$")
    private String issuedOfficer;
    @NotBlank(message = "Book ID cannot be empty")
    @Pattern(regexp = "^B\\d{6}$", message = "Invalid book Id")
    private String bookId;
    @NotBlank(message = "User ID cannot be empty")
    @Pattern(regexp = "^U\\d{6}$", message = "Invalid user Id")
    private String userId;

    public IssueTO(Date issuedDate, IssueStatus status, Integer renews, String issuedOfficer, String bookId, String userId) {
        this.issuedDate = issuedDate;
        this.status = status;
        this.renews = renews;
        this.issuedOfficer = issuedOfficer;
        this.bookId = bookId;
        this.userId = userId;
    }
}
