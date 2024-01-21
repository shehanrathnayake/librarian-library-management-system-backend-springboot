package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "issue")
public class Issue implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 20)
    private String issuedDate;
    @Column(nullable = false, length = 10)
    private String status;
    @Column(nullable = false, columnDefinition = "DEFAULT 0")
    private int renews;

    @ManyToOne
    @JoinColumn(name = "issued_officer", referencedColumnName = "id")
    private Staff issuedOfficer;

    @ManyToOne
    @JoinColumn(nullable = false, name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;
}
