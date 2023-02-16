package com.tamkstudents.cookbook.Domain.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class LoginCredentials {
    private final String username;
    private final String email;
    private final String rawPassword;
}
