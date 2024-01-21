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
    @Column(name = "isbn_number", nullable = false, length = 20, unique = true)
    private String isbnNumber;
    @Column(nullable = false, length = 60)
    private String name;
    @Column(nullable = false, length = 500)
    private String description;
    @Column(name = "book_cover", nullable = false, length = 100)
    private String bookCover;
    @Column(nullable = false, length = 100)
    private String author;
    @Column(nullable = false, length = 20)
    private String category;
    @Column(name = "total_copies", nullable = false)
    private int totalCopies;
    @Column(name = "available_copies", nullable = false)
    private int availableCopies;

    public Book(String isbnNumber, String name, String description, String bookCover, String author, String category, int totalCopies, int availableCopies) {
        this.isbnNumber = isbnNumber;
        this.name = name;
        this.description = description;
        this.bookCover = bookCover;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public Book(String isbnNumber, String name, String description, String author, String category, int totalCopies, int availableCopies) {
        this.isbnNumber = isbnNumber;
        this.name = name;
        this.description = description;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }
}
