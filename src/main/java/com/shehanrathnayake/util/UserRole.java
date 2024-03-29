package com.shehanrathnayake.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    MEMBER("member"),
    STAFF("staff");
    private final String role;
    UserRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }
}
