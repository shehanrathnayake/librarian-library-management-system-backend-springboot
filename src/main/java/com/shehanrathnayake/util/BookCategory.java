package com.shehanrathnayake.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BookCategory {
    ENTERTAINMENT("entertainment"),
    BIOGRAPHIES("biographies"),
    BUSINESS("business"),
    COMICS("comics"),
    TECHNOLOGY("technology"),
    FANTASY("fantasy"),
    HEALTH("health"),
    HISTORY("history"),
    SCI_FI("sci-fi"),
    KIDS("kids"),
    ALL("all");

    private final String category;
    BookCategory(String category) {
        this.category = category;
    }
    @JsonValue
    public String getCategory() {
        return category;
    }
}
