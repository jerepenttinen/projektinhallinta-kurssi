package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Mapper.LoginMapperService;
import com.tamkstudents.cookbook.Controller.Reply.CurrentUserReply;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController extends AbstractController {
    private final LoginMapperService loginMapperService;

    @GetMapping("/current")
    ResponseEntity<CurrentUserReply> getCurrentUser(@Parameter(hidden = true) LoginUserDao loginUserDao) {
        if (loginUserDao == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(loginMapperService.loginUserDaoToCurrentUserReply(loginUserDao), HttpStatus.OK);
    }
}
