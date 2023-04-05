package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Mapper.LoginMapperService;
import com.tamkstudents.cookbook.Controller.Reply.CurrentUserReply;
import com.tamkstudents.cookbook.Controller.Reply.GetUserReply;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final LoginMapperService loginMapperService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    public ResponseEntity<CurrentUserReply> getCurrentUser(@Parameter(hidden = true) LoginUserDao loginUserDao) {
        if (loginUserDao == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(loginMapperService.loginUserDaoToCurrentUserReply(loginUserDao));
    }

    @PostMapping
    public ResponseEntity<List<GetUserReply>> getUsers(@RequestBody List<Long> userIds) {
        var users = userService.getAll(userIds);
        return new ResponseEntity<>(users.stream().map(user -> new GetUserReply(user.getUserName())).toList(), HttpStatus.OK);
    }

}
