package com.shehanrathnayake.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IssueStatus {
    ISSUED("issued"),
    BORROWED("borrowed"),
    RENEWED("renewed"),
    RETURNED("returned"),
    RETURNED_LATE("returned-late");
    private final String status;

    IssueStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
