package com.tamkstudents.cookbook.Controller.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;
}
