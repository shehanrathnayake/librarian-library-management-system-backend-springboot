package com.shehanrathnayake.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Address cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid address format")
    private String address;
    @NotBlank(message = "Contact cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Contact cannot be empty")
    private String contact;
    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Invalid email format")
    private String email;
    @NotBlank(message = "Role cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+")
    private String role;
}
