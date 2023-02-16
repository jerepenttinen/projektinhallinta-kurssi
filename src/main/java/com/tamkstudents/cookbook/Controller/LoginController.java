package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
import com.tamkstudents.cookbook.Domain.login.LoginCredentials;
import com.tamkstudents.cookbook.Service.LoginService;
import com.tamkstudents.cookbook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping
public class LoginController extends AbstractController{

    Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @PostMapping("login/{loginType}")
    @ResponseBody
    public ResponseEntity<LoginUserDto> login(@PathVariable("loginType") String loginType,
                                              @RequestBody LoginCredentials credentials){
        if(!loginService.isLoggedIn()){
            LoginUserDto loginUserDto = loginService
                    .validateAndGetUser(credentials.getEmail(), credentials.getUsername());
            if(loginService.attemptLogin(credentials.getRawPassword(), loginUserDto)){
                return new ResponseEntity<>(loginUserDto, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }



}
