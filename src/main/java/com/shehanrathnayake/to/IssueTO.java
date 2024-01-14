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
import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class IssueTO implements Serializable {
    @Null(message = "Issue ID should be empty")
    private String issuedDateTime;
    @NotNull(message = "Status cannot be empty")
    private IssueStatus status;
    @NotBlank(message = "Book ID cannot be empty")
    @Pattern(regexp = "^B\\d{6}$", message = "Invalid book Id")
    private String bookId;
    @NotBlank(message = "User ID cannot be empty")
    @Pattern(regexp = "^U\\d{6}$", message = "Invalid user Id")
    private String userId;
    @Pattern(regexp = "^\\d{10,32}$", message = "Invalid date and time")
    private String returnedDateTime;
    @Pattern(regexp = "^-?\\d+(\\.\\d+)?$", message = "Invalid format")
    private BigDecimal fine;

    public IssueTO(IssueStatus status, String bookId, String userId) {
        this.status = status;
        this.bookId = bookId;
        this.userId = userId;
    }

    public IssueTO(IssueStatus status, String bookId, String userId, String returnedDateTime, BigDecimal fine) {
        this.status = status;
        this.bookId = bookId;
        this.userId = userId;
        this.returnedDateTime = returnedDateTime;
        this.fine = fine;
    }
}
