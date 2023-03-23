package com.tamkstudents.cookbook.Controller.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.With;


@Value
public class SignUpRequest {
    @With
    @NotBlank
    String username;

    @With
    @NotBlank
    String firstname;

    @With
    @NotBlank
    String lastname;

    @With
    @Email
    @NotBlank
    String email;

    @With
    @Size(min = 8, max = 72)
    @NotBlank
    String password;
}
