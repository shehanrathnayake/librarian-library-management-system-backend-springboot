package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "`return`")
public class Return implements SuperEntity{
    @Id
    @Column(name = "returned_date_time", length = 20)
    private String returnedDateTime;

    @OneToOne
    @JoinColumn(nullable = false, name = "issued_date_time", referencedColumnName = "issued_date_time")
    private Issue issue;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal fine;
}
