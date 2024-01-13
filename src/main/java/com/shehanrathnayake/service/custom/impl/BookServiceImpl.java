package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.service.custom.BookService;
import com.shehanrathnayake.service.util.BookCategory;
import com.shehanrathnayake.to.BookTO;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public BookTO saveBook(BookTO bookTO) {
        return null;
    }

    @Override
    public void updateBookDetails(BookTO bookTO) {

    }

    @Override
    public void deleteBook(Integer bookId) {

    }

    @Override
    public BookTO getBookDetails(Integer bookId) {
        return null;
    }

    @Override
    public List<BookTO> getAllBooks(BookCategory category) {
        return null;
    }
}
