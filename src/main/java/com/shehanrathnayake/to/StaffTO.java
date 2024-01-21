package com.shehanrathnayake.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Data @AllArgsConstructor @NoArgsConstructor
public class StaffTO implements Serializable {
    @Null(message = "Staff ID should be empty")
    private String id;
    @NotBlank(message = "Staff names cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid name")
    private String name;
    @NotBlank(message = "Designation cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9-. ]+$")
    private String designation;
    @NotBlank(message = "Address cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9-. ]$", message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Contact number cannot be empty")
    @Pattern(regexp = "^\\d+$")
    private String contact;
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public StaffTO(String name, String designation, String address, String contact, String password) {
        this.name = name;
        this.designation = designation;
        this.address = address;
        this.contact = contact;
        this.password = password;
    }
}
