package com.shehanrathnayake.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookCoverConstraintValidator implements ConstraintValidator<BookCover, MultipartFile> {
    private long maximumFileSize;
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) return true;
        if (multipartFile.getContentType() == null || !multipartFile.getContentType().startsWith("image/")) return false;
        return multipartFile.getSize() <= maximumFileSize;
    }

    @Override
    public void initialize(BookCover constraintAnnotation) {
        maximumFileSize = constraintAnnotation.maximumFileSize();
    }
}
