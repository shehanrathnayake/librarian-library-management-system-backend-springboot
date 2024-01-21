package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 50)
    private String designation;
    @Column(nullable = false, length = 500)
    private String address;
    @Column(nullable = false, length = 20)
    private String contact;
    @Column(nullable = false, length = 100)
    private String password;

    public Employee(String name, String designation, String address, String contact, String password) {
        this.name = name;
        this.designation = designation;
        this.address = address;
        this.contact = contact;
        this.password = password;
    }
}
