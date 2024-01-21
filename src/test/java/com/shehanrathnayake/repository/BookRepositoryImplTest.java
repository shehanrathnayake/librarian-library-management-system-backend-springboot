package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryImplTest {
    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void save() {
        Book book = new Book(
                "1234567891234",
                "Arts and Music",
                "A great masterpiece done by Author",
                "books/1",
                "Peter Alwis",
                "entertainment",
                5,
                3
        );
        Book savedBook = bookRepository.save(book);
        assertNotNull(savedBook);
        assertEquals(book.getId(), 1);
        assertEquals(book.getIsbnNumber(), "1234567891234");
        assertEquals(book.getBookCover(), "books/1");
        assertEquals(book.getAuthor(), "Shehan Rathnayake");
        assertEquals(book.getCategory(), "entertainment");


    }
    @Test
    void update() {

    }
    @Test
    void deleteById() {

    }
    @Test
    void findById() {

    }
    @Test
    void existById() {

    }
    @Test
    void findAll() {

    }
    @Test
    void count() {

    }
}