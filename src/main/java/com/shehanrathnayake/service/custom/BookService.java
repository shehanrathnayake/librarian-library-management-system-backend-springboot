package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.service.util.BookCategory;
import com.shehanrathnayake.to.BookTO;

import java.util.List;

public interface BookService extends SuperService {
    BookTO saveBook(BookTO bookTO);
    void updateBookDetails(BookTO bookTO);
    void deleteBook(Integer bookId);
    BookTO getBookDetails(Integer bookId);
    List<BookTO> getAllBooks(BookCategory category);
}
