package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.BookCategoryConverter;
import com.shehanrathnayake.entity.Book;
import com.shehanrathnayake.to.BookTO;
import com.shehanrathnayake.to.request.BookReqTO;
import com.shehanrathnayake.util.BookCategory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTransformer {
    private final ModelMapper mapper;
    private final BookCategoryConverter bookCategoryConverter;

    public BookTransformer(ModelMapper modelMapper, BookCategoryConverter bookCategoryConverter) {
        this.mapper = modelMapper;
        this.bookCategoryConverter = bookCategoryConverter;

        mapper.typeMap(MultipartFile.class, String.class)
                .setConverter(ctx -> null);

        mapper.typeMap(String.class, Integer.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? Integer.parseInt(ctx.getSource().substring(1)) : null);

        mapper.typeMap(Integer.class, String.class)
                        .setConverter(ctx -> (ctx.getSource() != null) ? String.format("B%06d", ctx.getSource()) : null);

        mapper.typeMap(BookCategory.class, String.class)
                .setConverter(ctx -> ctx.getSource().getCategory());

        mapper.typeMap(String.class, BookCategory.class)
                .setConverter(ctx -> bookCategoryConverter.convert(ctx.getSource()));

    }

    public Book fromBookReqTO(BookReqTO bookReqTO) {
        return mapper.map(bookReqTO, Book.class);
    }
    public Book fromBookTO(BookTO bookTO) {
        return mapper.map(bookTO, Book.class);
    }
    public BookTO toBookTO(Book book) {
        return mapper.map(book, BookTO.class);
    }
    public List<BookTO> toBookTOList(List<Book> bookList) {
        return bookList.stream().map(this::toBookTO).collect(Collectors.toList());
    }
}
