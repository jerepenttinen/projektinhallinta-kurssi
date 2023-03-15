package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
import com.tamkstudents.cookbook.Domain.login.SignInCredentials;
import com.tamkstudents.cookbook.Domain.login.SignUpCredentials;
import com.tamkstudents.cookbook.Service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@DependsOn("securityFilterChain")
public class LoginController extends AbstractController{
    private final LoginService loginService;
    private final RememberMeServices rememberMeServices;
    @PostMapping("/signin")
    public ResponseEntity<Void> signIn(@RequestBody SignInCredentials signInCredentials, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (request.getUserPrincipal() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            request.login(signInCredentials.getEmail(), signInCredentials.getPassword());
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        var authentication = (Authentication)request.getUserPrincipal();

        rememberMeServices.loginSuccess(request, response, authentication);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpCredentials signUpCredentials, BindingResult bindingResult, HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            if (loginService.createNewUser(signUpCredentials) != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            // ???
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> signOut(HttpServletRequest request) throws ServletException {
        request.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
