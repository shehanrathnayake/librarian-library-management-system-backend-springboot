package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
