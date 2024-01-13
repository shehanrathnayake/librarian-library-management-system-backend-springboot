package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "issue")
public class Issue implements SuperEntity {
    @Id
    @Column(name = "issued_date_time", length = 20)
    private String issuedDateTime;
    @Column(nullable = false, length = 10)
    private String status;

    @ManyToOne
    @JoinColumn(nullable = false, name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(length = 20)
    private String returnedDateTime;
    @Column(precision = 10, scale = 2)
    private BigDecimal fine;

    public Issue(String issuedDateTime, String status, Book book, User user) {
        this.issuedDateTime = issuedDateTime;
        this.status = status;
        this.book = book;
        this.user = user;
    }
}
