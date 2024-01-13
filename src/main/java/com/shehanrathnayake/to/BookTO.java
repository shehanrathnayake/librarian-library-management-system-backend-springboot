package com.shehanrathnayake.to;

import com.shehanrathnayake.service.util.BookCategory;
import com.shehanrathnayake.validation.BookCover;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Data @AllArgsConstructor @NoArgsConstructor
public class BookTO implements Serializable {
    @Null(message = "Book ID should be null")
    private String id;
    @NotBlank(message = "ISBN number cannot be empty")
    @Pattern(regexp = "^\\d{13}$", message = "Not a valid ISBN number")
    private String isbnNumber;
    @NotBlank(message = "Book Cover cannot be empty")
    @BookCover
    private MultipartFile bookCover;
    @NotBlank(message = "Author cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$")
    private String author;
    @NotNull(message = "Book category cannot be empty")
    private BookCategory category;
    @NotNull(message = "Availability cannot be empty")
    private boolean isAvailable;

    public BookTO(String isbnNumber, MultipartFile bookCover, String author, BookCategory category, boolean isAvailable) {
        this.isbnNumber = isbnNumber;
        this.bookCover = bookCover;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;
    }
}
