package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "issue_return")
public class IssueReturn implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String returnedDate;

    @OneToOne
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    private Issue issueId;

    @ManyToOne
    @JoinColumn(name = "returned_officer", referencedColumnName = "id")
    private Staff returnedOfficer;

    @Column(precision = 10, scale = 2)
    private BigDecimal fine;
}
