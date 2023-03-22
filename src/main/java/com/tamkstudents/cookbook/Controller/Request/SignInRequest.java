package com.tamkstudents.cookbook.Controller.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
public class SignInRequest {
    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;
}
