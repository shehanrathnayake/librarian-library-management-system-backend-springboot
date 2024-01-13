package com.shehanrathnayake.service.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    MEMBER("member"),
    STAFF("staff");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
    @JsonValue
    public String getRole() {
        return role;
    }
}
