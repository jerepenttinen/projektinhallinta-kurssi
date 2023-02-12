package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Service.LoginService;
import com.tamkstudents.cookbook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;


}
