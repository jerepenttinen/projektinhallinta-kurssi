package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.Principal;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class UserControllerAdvice {
    private final LoginService loginService;

    @ModelAttribute("loginUserDao")
    public LoginUserDao addLoginUserDaoToModel(Principal principal, HttpServletRequest request) {
        if (principal == null) {
            request.getSession().invalidate();
            return null;
        }

        try {
            return (LoginUserDao) loginService.loadUserByUsername(principal.getName());
        } catch (UsernameNotFoundException e) {
            request.getSession().invalidate();
            return null;
        }
    }
}
