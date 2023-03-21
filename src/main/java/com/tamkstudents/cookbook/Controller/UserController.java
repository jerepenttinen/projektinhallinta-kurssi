package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Mapper.LoginMapperService;
import com.tamkstudents.cookbook.Controller.Reply.CurrentUserReply;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final LoginMapperService loginMapperService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    ResponseEntity<CurrentUserReply> getCurrentUser(@Parameter(hidden = true) Optional<LoginUserDao> loginUserDao) {
        var loginUser = loginUserDao.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        return ResponseEntity.ok(loginMapperService.loginUserDaoToCurrentUserReply(loginUser));
    }
}
