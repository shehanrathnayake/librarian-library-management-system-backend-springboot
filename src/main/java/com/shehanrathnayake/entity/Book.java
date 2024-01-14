package com.shehanrathnayake.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "isbn_number", nullable = false, length = 20)
    private String isbnNumber;
    @Column(name = "book_cover", nullable = false, length = 100)
    private String bookCover;
    @Column(nullable = false, length = 100)
    private String author;
    @Column(nullable = false, length = 20)
    private String category;
    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    public Book(String isbnNumber, String bookCover, String author, String category, boolean isAvailable) {
        this.isbnNumber = isbnNumber;
        this.bookCover = bookCover;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public Book(String isbnNumber, String author, String category, boolean isAvailable) {
        this.isbnNumber = isbnNumber;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;
    }
}
