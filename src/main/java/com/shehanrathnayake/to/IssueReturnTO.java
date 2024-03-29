package com.shehanrathnayake.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class IssueReturnTO implements Serializable {
    @Null(message = "Return id should be empty")
    private String id;
    @NotNull(message = "Returned date cannot be empty")
    private Date returnedDate;
    @NotBlank(message = "Issue id cannot be empty")
    @Pattern(regexp = "^I\\d{6}$")
    private String issueId;
    @NotBlank(message = "Returned officer id cannot be empty")
    @Pattern(regexp = "^E\\d{6}$")
    private String returnedEmployee;
    @NotNull(message = "Fine cannot be empty. No no fine, simply send zero")
    private BigDecimal fine;
}
