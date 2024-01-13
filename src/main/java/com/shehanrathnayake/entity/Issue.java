package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
