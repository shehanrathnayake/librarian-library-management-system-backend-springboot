package com.shehanrathnayake.to.request;

import com.shehanrathnayake.util.BookCategory;
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
public class BookReqTO implements Serializable {
    @Null(message = "Book ID should be null")
    private String id;
    @NotBlank(message = "ISBN number cannot be empty")
    @Pattern(regexp = "^\\d{13}$", message = "Not a valid ISBN number")
    private String isbnNumber;
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z-.& ]{3,}$", message = "Not a valid name")
    private String name;
    @NotBlank(message = "Description cannot be empty")
    @Pattern(regexp = "^[A-Za-z-.& ]{5,}$", message = "Not a valid description")
    private String description;
    @BookCover
    private MultipartFile bookCover;
    @NotBlank(message = "Author cannot be empty")
    @Pattern(regexp = "^[A-Za-z-.& ]+$")
    private String author;
    @NotNull(message = "Book category cannot be empty")
    private BookCategory category;
    @NotNull(message = "No of Total copies cannot be empty")
    private Integer totalCopies;
    @NotNull(message = "No. of available copies cannot be empty. Provide 0 if all copies are issued")
    private Integer availableCopies;

    public BookReqTO(String isbnNumber, String name, String description, MultipartFile bookCover, String author, BookCategory category, Integer totalCopies, Integer availableCopies) {
        this.isbnNumber = isbnNumber;
        this.name = name;
        this.description = description;
        this.bookCover = bookCover;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }
}
