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

    public BookTransformer(ModelMapper modelMapper, BookPropertiesConverter bookPropsConverter) {
        this.mapper = modelMapper;

        mapper.typeMap(MultipartFile.class, String.class)
                .setConverter(ctx -> null);

        mapper.typeMap(String.class, Integer.class)
                .setConverter(ctx -> (ctx.getSource() != null) ? bookPropsConverter.convertIdToInt(ctx.getSource()) : null);

        mapper.typeMap(Integer.class, String.class)
                        .setConverter(ctx -> (ctx.getSource() != null) ? bookPropsConverter.covertIdToString(ctx.getSource()) : null);

        mapper.typeMap(BookCategory.class, String.class)
                .setConverter(ctx -> ctx.getSource().getCategory());

        mapper.typeMap(String.class, BookCategory.class)
                .setConverter(ctx -> bookPropsConverter.convert(ctx.getSource()));

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
