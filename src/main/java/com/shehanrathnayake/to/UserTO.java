package com.shehanrathnayake.to;

import com.shehanrathnayake.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Data @AllArgsConstructor @NoArgsConstructor
public class UserTO implements Serializable {
    @Null(message = "User ID should be empty")
    private String id;
    @NotBlank(message = "User's name cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid name format")
    private String name;
    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotBlank(message = "Address cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9-., ]+$", message = "Invalid address format")
    private String address;
    @NotBlank(message = "Contact cannot be empty")
    @Pattern(regexp = "^\\d+$", message = "Contact cannot be empty")
    private String contact;
    @NotNull(message = "User role cannot be null")
    private UserRole roles;

    public UserTO(String name, String email, String password, String address, String contact, UserRole roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.roles = roles;
    }
}
