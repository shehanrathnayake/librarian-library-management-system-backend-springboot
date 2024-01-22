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
    private final BookTransformer bookTransformer;

    public BookServiceImpl(BookRepository bookRepository, Bucket bucket, BookTransformer bookTransformer) {
        this.bookRepository = bookRepository;
        this.bucket = bucket;
        this.bookTransformer = bookTransformer;
    }

    @Override
    public BookTO saveBook(BookReqTO bookReqTO) {
        Book book = bookTransformer.fromBookReqTO(bookReqTO);
        book.setBookCover("book/0");
        Book savedBook = bookRepository.save(book);

        savedBook.setBookCover("book/" + savedBook.getId());
        Book updatedBook = bookRepository.save(savedBook);
        BookTO bookTO = bookTransformer.toBookTO(updatedBook);
        System.out.println("bookTO-id: " + bookTO.getId());

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
        Book currentBook = bookRepository.findById(getIdNumberValue(bookReqTO.getId())).orElseThrow(() -> new AppException(404, "No book associated with the id"));

        Book updatedBook = bookTransformer.fromBookReqTO(bookReqTO);
        updatedBook.setBookCover("book/" + updatedBook.getId());
        bookRepository.save(updatedBook);

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
        Book foundBook = bookRepository.findById(getIdNumberValue(bookTO.getId())).orElseThrow(() -> new AppException(404, "No book associate with the id"));
        Book updatedBook = bookTransformer.fromBookTO(bookTO);
        updatedBook.setBookCover(foundBook.getBookCover());
        bookRepository.save(updatedBook);
    }

    @Override
    public void deleteBook(String bookId) {
        int idNumValue = getIdNumberValue(bookId);
        Book foundBook = bookRepository.findById(getIdNumberValue(bookId)).orElseThrow(() -> new AppException(404, "No book associate with the id"));
        bookRepository.deleteById(idNumValue);

        boolean isDeleted = bucket.get(foundBook.getBookCover()).delete();
        if (!isDeleted) throw new AppException(500, "Failed to update the book cover");
    }

    @Override
    public BookTO getBookDetails(String bookId) {
        int idNumValue = getIdNumberValue(bookId);

        Optional<Book> optBook = bookRepository.findById(idNumValue);
        if (optBook.isEmpty()) throw new AppException(404, "Book not found");
        BookTO bookTO = bookTransformer.toBookTO(optBook.get());
        bookTO.setBookCover(bucket.get(optBook.get().getBookCover()).signUrl(1, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature()).toString());
        return bookTO;
    }

    @Override
    public List<BookTO> getAllBooksByCategory(List<BookCategory> category) {
        List<Book> bookList = null;
        if (category.size() == 1 && category.get(0) == BookCategory.ALL) bookList = bookRepository.findAll();
        else bookList = bookRepository.findBooksByCategoryIn(category.stream().map(BookCategory::getCategory).collect(Collectors.toList()));
        return convertBookListToBookToList(bookList);
    }

//    @Override
//    public List<BookTO> getAllBooks() {
//        List<Book> allBookList = bookRepository.findAll();
//        return convertBookListToBookToList(allBookList);
//    }


    private List<BookTO> convertBookListToBookToList(List<Book> bookList) {
        List<BookTO> bookTOList = bookTransformer.toBookTOList(bookList);
        return bookTOList.stream().map(l -> {
            l.setBookCover(bucket.get(l.getBookCover()).signUrl(1, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature()).toString());
            return l;
        }).collect(Collectors.toList());
    }

    private int getIdNumberValue(String bookId) {
        return Integer.parseInt(bookId.substring(1));
    }
}
