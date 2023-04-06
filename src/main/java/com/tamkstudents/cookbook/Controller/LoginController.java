package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Request.SignInRequest;
import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Service.Exceptions.EmailOrUsernameTakenException;
import com.tamkstudents.cookbook.Service.Exceptions.FailedToCreateUserException;
import com.tamkstudents.cookbook.Service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
@RequiredArgsConstructor
@DependsOn("securityFilterChain")
public class LoginController {
    private final LoginService loginService;
    private final RememberMeServices rememberMeServices;

    @Operation(summary = "Sign in", responses = {
            @ApiResponse(responseCode = "200", description = "User signed in successfully and authentication cookie should be set"),
            @ApiResponse(responseCode = "400", description = "Request body validation failed"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "403", description = "Already signed in"),
    })
    @PostMapping("/signin")
    public void signIn(@Valid @RequestBody SignInRequest signInRequest, HttpServletRequest request, HttpServletResponse response) {
        if (request.getUserPrincipal() != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Olet jo kirjautunut sisään");
        }

        try {
            request.login(signInRequest.getEmail(), signInRequest.getPassword());
        } catch (ServletException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Virheelliset kirjautumistiedot");
        }

        var authentication = (Authentication) request.getUserPrincipal();
        rememberMeServices.loginSuccess(request, response, authentication);
    }

    @Operation(summary = "Sign up new user", responses = {
            @ApiResponse(responseCode = "201", description = "User signed up successfully"),
            @ApiResponse(responseCode = "400", description = "Request body validation failed"),
            @ApiResponse(responseCode = "403", description = "Already signed in"),
            @ApiResponse(responseCode = "409", description = "Email or username is already taken"),
            @ApiResponse(responseCode = "500", description = "Failed to create user"),
    })
    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User signed up successfully")
    public void signUp(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Olet jo kirjautunut sisään");
        }

        try {
            loginService.createNewUser(signUpRequest);
        } catch (EmailOrUsernameTakenException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Sähköposti tai käyttäjänimi on jo otettu", e);
        } catch (FailedToCreateUserException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Käyttäjän luominen epäonnistui", e);
        }
    }

    @PostMapping("/signout")
    public void signOut(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
