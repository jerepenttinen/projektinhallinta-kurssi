package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
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
    @GetMapping("/current")
    ResponseEntity<LoginUserDto> getCurrentUser(LoginUserDao loginUserDao) {
        if (loginUserDao == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new LoginUserDto(loginUserDao), HttpStatus.OK);
    }
}
