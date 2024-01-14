package com.shehanrathnayake.service.custom.impl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.shehanrathnayake.entity.Book;
import com.shehanrathnayake.exception.AppException;
import com.shehanrathnayake.repository.BookRepository;
import com.shehanrathnayake.service.custom.BookService;
import com.shehanrathnayake.service.util.BookTransformer;
import com.shehanrathnayake.to.BookTO;
import com.shehanrathnayake.to.request.BookReqTO;
import com.shehanrathnayake.util.BookCategory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final Bucket bucket;
    private final BookTransformer transformer;

    public BookServiceImpl(BookRepository bookRepository, Bucket bucket, BookTransformer transformer) {
        this.bookRepository = bookRepository;
        this.bucket = bucket;
        this.transformer = transformer;
    }

    @Override
    public BookTO saveBook(BookReqTO bookReqTO) {
        System.out.println("Before repo: " + bookReqTO.isAvailable());
        Book book = transformer.fromBookReqTO(bookReqTO);
        System.out.println("After repo: " + book.isAvailable());
        book.setBookCover("book/0");
        Book savedBook = bookRepository.save(book);

        savedBook.setBookCover("book/" + savedBook.getId());
        Book updatedBook = bookRepository.save(savedBook);
        BookTO bookTO = transformer.toBookTO(updatedBook);
        System.out.println(bookTO.isAvailable());

        Blob blobRef = null;
        try {
            blobRef = bucket.create(savedBook.getBookCover(), bookReqTO.getBookCover().getInputStream(), bookReqTO.getBookCover().getContentType());
        } catch (IOException e) {
            throw new AppException(500, "Failed to upload the image", e);
        }
        String signUrl = blobRef.signUrl(1, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature()).toString();
        bookTO.setBookCover(signUrl);
        return bookTO;
    }

    @Override
    public void updateBookDetails(BookReqTO bookReqTO) {
        Book currentBook = bookRepository.findById(Integer.parseInt(bookReqTO.getId().substring(1))).orElseThrow(() -> new AppException(404, "No book associated with the id"));

        Book updatedBook = transformer.fromBookReqTO(bookReqTO);
        updatedBook.setBookCover("book/" + updatedBook.getId());

        boolean isDeleted = bucket.get(currentBook.getBookCover()).delete();
        if (!isDeleted) throw new AppException(500, "Failed to update the book cover");

        try {
            bucket.create(updatedBook.getBookCover(), bookReqTO.getBookCover().getInputStream(), bookReqTO.getBookCover().getContentType());
        } catch (IOException e) {
            throw new AppException(500, "Failed to update the book cover.", e);
        }
    }

    @Override
    public void updateBookDetails(BookTO bookTO) {
        bookRepository.findById(Integer.parseInt(bookTO.getId().substring(1))).orElseThrow(() -> new AppException(404, "No book associate with the id"));
        Book updatedBook = transformer.fromBookTO(bookTO);
        bookRepository.save(updatedBook);
    }

    @Override
    public void deleteBook(String bookId) {
        int idNumValue = getIdNumberValue(bookId);
        if (bookRepository.existsById(idNumValue)) {
            bookRepository.deleteById(idNumValue);
        } else {
            throw new AppException(404, "Book not found");
        }
    }

    @Override
    public BookTO getBookDetails(String bookId) {
        int idNumValue = getIdNumberValue(bookId);

        Optional<Book> optBook = bookRepository.findById(idNumValue);
        if (optBook.isEmpty()) throw new AppException(404, "Book not found");
        BookTO bookTO = transformer.toBookTO(optBook.get());
        bookTO.setBookCover(bucket.get(optBook.get().getBookCover()).signUrl(1, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature()).toString());
        return bookTO;
    }

    @Override
    public List<BookTO> getAllBooks(BookCategory category) {
        if (category == null) throw new  AppException(400, "Bad Request");
        List<Book> bookList = bookRepository.findBooksByCategory(category);
        List<BookTO> bookTOList = transformer.toBookTOList(bookList);
        return bookTOList.stream().map(l -> {
            l.setBookCover(bucket.get(l.getBookCover()).signUrl(1, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature()).toString());
            return l;
        }).collect(Collectors.toList());
    }

    private int getIdNumberValue(String bookId) {
        return Integer.parseInt(bookId.substring(1));
    }
}
