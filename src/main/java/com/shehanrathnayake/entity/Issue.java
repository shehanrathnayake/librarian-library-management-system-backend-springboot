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
    @Column(nullable = false)
    private int renews;

    @ManyToOne
    @JoinColumn(nullable = false, name = "issued_employee", referencedColumnName = "id")
    private Employee issuedEmployee;

    @ManyToOne
    @JoinColumn(nullable = false, name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;
}
