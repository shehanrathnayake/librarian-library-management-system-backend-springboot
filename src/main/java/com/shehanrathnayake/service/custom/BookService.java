package com.shehanrathnayake.service.custom;

import com.shehanrathnayake.service.SuperService;
import com.shehanrathnayake.service.util.BookCategory;
import com.shehanrathnayake.to.BookTO;
import com.shehanrathnayake.to.request.BookReqTO;

import java.util.List;

public interface BookService extends SuperService {
    BookTO saveBook(BookReqTO bookReqTO);
    void updateBookDetails(BookReqTO bookReqTO);
    void updateBookDetails(BookTO bookTO);
    void deleteBook(String bookId);
    BookTO getBookDetails(String bookId);
    List<BookTO> getAllBooks(BookCategory category);
}
