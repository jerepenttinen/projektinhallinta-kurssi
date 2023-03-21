package com.tamkstudents.cookbook.Controller.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    String username;

    @NotBlank
    String firstname;

    @NotBlank
    String lastname;

    @Email
    @NotBlank
    String email;

    @Size(min = 8, max = 72)
    @NotBlank
    String password;
}
