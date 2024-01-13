package com.shehanrathnayake.service.custom.impl;

import com.shehanrathnayake.service.custom.BookService;
import com.shehanrathnayake.service.util.BookCategory;
import com.shehanrathnayake.to.BookTO;
import com.shehanrathnayake.to.request.BookReqTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Override
    public BookTO saveBook(BookReqTO bookReqTO) {
        return null;
    }

    @Override
    public void updateBookDetails(BookReqTO bookReqTO) {

    }

    @Override
    public void updateBookDetails(BookTO bookTO) {

    }

    @Override
    public void deleteBook(String bookId) {

    }

    @Override
    public BookTO getBookDetails(String bookId) {
        return null;
    }

    @Override
    public List<BookTO> getAllBooks(BookCategory category) {
        return null;
    }
}
