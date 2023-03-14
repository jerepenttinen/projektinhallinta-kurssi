package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
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
            log.info("loading {}", principal.getName());
            var result = (LoginUserDao) loginService.loadUserByUsername(principal.getName());
            log.info("wtf {}", result.getEmail());
            return result;
        } catch (UsernameNotFoundException e) {
            request.getSession().invalidate();
            return null;
        }
    }
}
