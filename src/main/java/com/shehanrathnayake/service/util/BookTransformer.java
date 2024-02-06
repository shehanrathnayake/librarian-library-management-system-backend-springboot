package com.shehanrathnayake.service.util;

import com.shehanrathnayake.converter.BookPropertiesConverter;
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
    private final BookPropertiesConverter bookPropsConverter;

    public BookTransformer(ModelMapper modelMapper, BookPropertiesConverter bookPropsConverter) {
        this.mapper = modelMapper;
        this.bookPropsConverter = bookPropsConverter;

        mapper.typeMap(MultipartFile.class, String.class)
                .setConverter(ctx -> null);

        mapper.typeMap(BookCategory.class, String.class)
                .setConverter(ctx -> ctx.getSource().getCategory());

        mapper.typeMap(String.class, BookCategory.class)
                .setConverter(ctx -> bookPropsConverter.convert(ctx.getSource()));

    }

    public Book fromBookReqTO(BookReqTO bookReqTO) {
        return mapper.map(bookReqTO, Book.class);
    }
    public Book fromBookTO(BookTO bookTO) {
        Book book = mapper.map(bookTO, Book.class);
        if (bookTO.getId() != null)  book.setId(bookPropsConverter.convertIdToInt(bookTO.getId()));
        return book;
    }
    public BookTO toBookTO(Book book) {
        BookTO bookTO = mapper.map(book, BookTO.class);
        bookTO.setId(bookPropsConverter.covertIdToString(book.getId()));
        return bookTO;
    }
    public List<BookTO> toBookTOList(List<Book> bookList) {
        return bookList.stream().map(this::toBookTO).collect(Collectors.toList());
    }
}
