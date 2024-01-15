package com.shehanrathnayake.converter;

import com.shehanrathnayake.util.BookCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookCategoryConverter implements Converter<String, BookCategory> {
    @Override
    public BookCategory convert(String source) {
        for (BookCategory category : BookCategory.values()) {
            if (category.getCategory().equalsIgnoreCase(source)) {
                return category;
            }
        }
        return null;
    }
}