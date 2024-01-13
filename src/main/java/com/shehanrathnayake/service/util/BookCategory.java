package com.shehanrathnayake.service.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BookCategory {
    ENTERTAINMENT("entertainment"),
    BIOGRAPHIES("biographies"),
    BUSINESS("business"),
    COMICS("comics"),
    TECHNOLOGY("technology"),
    RELIGION("religion"),
    HEALTH("health"),
    HISTORY("history"),
    SCI_FI("sci-fi"),
    KIDS("kids");

    private String category;
    BookCategory(String category) {
        this.category = category;
    }
    @JsonValue
    public String getCategory() {
        return category;
    }
}
