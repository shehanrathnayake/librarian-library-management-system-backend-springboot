package com.shehanrathnayake.api;

import com.shehanrathnayake.converter.BookCategoryConverter;
import com.shehanrathnayake.service.custom.BookService;
import com.shehanrathnayake.util.BookCategory;
import com.shehanrathnayake.to.BookTO;
import com.shehanrathnayake.to.request.BookReqTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin
public class BookHttpController {
    private BookService bookService;
    private BookCategoryConverter bookCategoryConverter;

    public BookHttpController(BookService bookService, BookCategoryConverter bookCategoryConverter) {
        this.bookService = bookService;
        this.bookCategoryConverter = bookCategoryConverter;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public BookTO createNewBook(@ModelAttribute @Validated BookReqTO bookReqTO) {
        return bookService.saveBook(bookReqTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{book-id}", consumes = "multipart/form-data")
    public void updateBookDetailsViaMultipart(@PathVariable("book-id") String bookId,
                                              @ModelAttribute @Validated BookReqTO bookReqTO) {
        bookReqTO.setId(bookId);
        bookService.updateBookDetails(bookReqTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{book-id}", consumes = "application/json")
    public void updateBookDetailsViaJson(@PathVariable("book-id") String bookId,
                                         @RequestBody @Validated BookTO bookTO) {
        bookTO.setId(bookId);
        bookService.updateBookDetails(bookTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{book-id}")
    public void deleteBook(@PathVariable("book-id") String bookId) {
        bookService.deleteBook(bookId);
    }

    @GetMapping(value = "/{book-id}", produces = "application/json")
    public BookTO getBookDetails(@PathVariable("book-id") String bookId) {
        return bookService.getBookDetails(bookId);
    }

    @GetMapping(produces = "application/json")
    public List<BookTO> getAllBooksByCategory(@RequestParam String category) {
        BookCategory categoryType = bookCategoryConverter.convert(category);
        return bookService.getAllBooks(categoryType);
    }
}