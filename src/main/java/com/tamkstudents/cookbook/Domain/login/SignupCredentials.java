package com.tamkstudents.cookbook.Domain.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class SignupCredentials {
    @NotNull
    private final String username;
    @NotNull
    private final String firstname;
    @NotNull
    private final String lastname;
    @NotNull
    private final String email;
    @NotNull
    private final String rawPassword;
}
