package com.shehanrathnayake.service.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IssueStatus {
    ISSUED("issued"),
    BORROWED("borrowed"),
    RETURNED("returned"),
    RETURNED_LATE("returned-late");
    private String status;

    IssueStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
