package com.shehanrathnayake.to;

import com.shehanrathnayake.util.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z-.& ]{3,}$", message = "Not a valid name")
    private String name;
    @NotBlank(message = "Description cannot be empty")
    @Pattern(regexp = "^[A-Za-z-.& ]{5,}$", message = "Not a valid description")
    private String description;
    @NotBlank(message = "Book Cover cannot be empty")
    private String bookCover;
    @NotBlank(message = "Author cannot be empty")
    @Pattern(regexp = "^[A-Za-z-.& ]+$", message = "Not a valid name for author")
    private String author;
    @NotNull(message = "Book category cannot be empty")
    private BookCategory category;
    @NotNull(message = "No of Total copies cannot be empty")
    private Integer totalCopies;
    @NotNull(message = "No. of available copies cannot be empty. Provide 0 if all copies are issued")
    private Integer availableCopies;

    public BookTO(String isbnNumber, String name, String description, String bookCover, String author, BookCategory category, Integer totalCopies, Integer availableCopies) {
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
