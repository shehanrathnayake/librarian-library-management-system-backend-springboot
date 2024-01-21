package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 60, unique = true)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 500)
    private String address;
    @Column(nullable = false, length = 20)
    private String contact;

    public User(String name, String email, String password, String address, String contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contact = contact;
    }
}
