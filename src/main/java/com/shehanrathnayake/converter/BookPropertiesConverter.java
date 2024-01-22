package com.shehanrathnayake.converter;

import com.shehanrathnayake.util.BookCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookPropertiesConverter implements Converter<String, BookCategory> {
    @Override
    public BookCategory convert(String source) {
        for (BookCategory category : BookCategory.values()) {
            if (category.getCategory().equalsIgnoreCase(source)) {
                return category;
            }
        }
        return null;
    }

    public List<BookCategory> convertAll(List<String> sourceList) {
        List<BookCategory> bookCategories = new ArrayList<>();
        for (String source : sourceList) {
            bookCategories.add(this.convert(source));
        }
        return bookCategories;
    }

    public int convertIdToInt(String bookId) {
        return Integer.parseInt(bookId.substring(1));
    }
    public String covertIdToString(Integer bookId) {
        return String.format("B%06d", bookId);
    }
}