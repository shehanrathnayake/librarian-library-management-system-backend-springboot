package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryImplTest {
    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager em;

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
//        assertEquals(savedBook.getId(), 1);
        assertEquals(savedBook.getIsbnNumber(), book.getIsbnNumber());
        assertEquals(savedBook.getName(), book.getName());
        assertEquals(savedBook.getDescription(), book.getDescription());
        assertEquals(savedBook.getBookCover(), book.getBookCover());
        assertEquals(savedBook.getAuthor(), book.getAuthor());
        assertEquals(savedBook.getCategory(), book.getCategory());
        assertEquals(savedBook.getTotalCopies(), book.getTotalCopies());
        assertEquals(savedBook.getAvailableCopies(), book.getAvailableCopies());
    }
    @Test
    void update() {
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
        savedBook.setIsbnNumber("2314567891234");
        savedBook.setName("Mark Zuckerburg");
        savedBook.setDescription("This is an biography of Mark Zuckerburg");
        savedBook.setBookCover("book/2");
        savedBook.setAuthor("Alan Donald");
        savedBook.setCategory("biographies");
        savedBook.setTotalCopies(7);
        savedBook.setAvailableCopies(5);
        Book updatedBook = bookRepository.save(book);

//        assertEquals(updatedBook.getId(), 1);
        assertEquals(updatedBook.getIsbnNumber(), savedBook.getIsbnNumber());
        assertEquals(updatedBook.getName(), savedBook.getName());
        assertEquals(updatedBook.getDescription(), savedBook.getDescription());
        assertEquals(updatedBook.getBookCover(), savedBook.getBookCover());
        assertEquals(updatedBook.getAuthor(), savedBook.getAuthor());
        assertEquals(updatedBook.getCategory(), savedBook.getCategory());
        assertEquals(updatedBook.getTotalCopies(), savedBook.getTotalCopies());
        assertEquals(updatedBook.getAvailableCopies(), savedBook.getAvailableCopies());
    }
    @Test
    void deleteById() {
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
        Book foundBook = em.find(Book.class, savedBook.getId());
        assertNotNull(foundBook);
        bookRepository.delete(savedBook);
        Book foundBook2 = em.find(Book.class, savedBook.getId());
        assertNull(foundBook2);
    }
    @Test
    void findById() {
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
        Optional<Book> optFoundBook = bookRepository.findById(savedBook.getId());
        assertNotNull(optFoundBook.get());

        assertEquals(savedBook.getId(), optFoundBook.get().getId());
        assertEquals(savedBook.getName(), optFoundBook.get().getName());
        assertEquals(savedBook.getDescription(), optFoundBook.get().getDescription());
        assertEquals(savedBook.getBookCover(), optFoundBook.get().getBookCover());
        assertEquals(savedBook.getAuthor(), optFoundBook.get().getAuthor());
        assertEquals(savedBook.getCategory(), optFoundBook.get().getCategory());
        assertEquals(savedBook.getTotalCopies(), optFoundBook.get().getTotalCopies());
        assertEquals(savedBook.getAvailableCopies(), optFoundBook.get().getAvailableCopies());
    }
    @Test
    void existById() {
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

        assertTrue(bookRepository.existsById(savedBook.getId()));
        assertFalse(bookRepository.existsById(1254));
    }
    @Test
    void findAll() {
        Book book;
        for (int i = 0; i < 10; i++) {
            book = new Book(
                    "123456789123" + i,
                    "Arts and Music",
                    "A great masterpiece done by Author",
                    "books/" + i+1,
                    "Peter Alwis",
                    "entertainment",
                    5,
                    3
            );
            bookRepository.save(book);
        }
        assertTrue(bookRepository.findAll().size() == 10);
    }
    @Test
    void count() {
        Book book;
        for (int i = 0; i < 10; i++) {
            book = new Book(
                    "123456789123" + i,
                    "Arts and Music",
                    "A great masterpiece done by Author",
                    "books/" + i+1,
                    "Peter Alwis",
                    "entertainment",
                    5,
                    3
            );
            bookRepository.save(book);
        }
        assertTrue(bookRepository.count() == 10);
    }
}