package com.codewithmosh.store.dtos.User;

import com.codewithmosh.store.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUserRequest {

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Lowercase(message = "Email must be lowercase")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters.")
    private String password;
}
