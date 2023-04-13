package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Mapper.LoginMapperService;
import com.tamkstudents.cookbook.Controller.Mapper.UserMapperService;
import com.tamkstudents.cookbook.Controller.Reply.CurrentUserReply;
import com.tamkstudents.cookbook.Controller.Reply.GetUserReply;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import com.tamkstudents.cookbook.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private final UserMapperService userMapperService;
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
        return new ResponseEntity<>(users.stream().map(userMapperService::getUserReplyFromUserDao).toList(), HttpStatus.OK);
    }


    @Operation(summary = "Gets user details by id", responses = {
            @ApiResponse(responseCode = "200", description = "User details, description and image can be null"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserReply> getUser(@PathVariable Long userId) {
        try {
            var user = userService.getById(userId);
            return new ResponseEntity<>(userMapperService.getUserReplyFromUserDao(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
