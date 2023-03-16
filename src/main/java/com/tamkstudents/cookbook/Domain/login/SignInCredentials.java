package com.tamkstudents.cookbook.Domain.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class SignInCredentials {
    @NotNull
    private final String email;
    @NotNull
    private final String password;
}
