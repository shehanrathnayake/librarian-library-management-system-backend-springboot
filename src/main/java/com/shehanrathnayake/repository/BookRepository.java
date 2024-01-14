package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Book;
import com.shehanrathnayake.util.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByCategory(BookCategory category);

}
