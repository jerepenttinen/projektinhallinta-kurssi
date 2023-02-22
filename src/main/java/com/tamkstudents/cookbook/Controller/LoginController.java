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
@RequestMapping("/api/")
public class LoginController extends AbstractController{

    Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<LoginUserDto> login(@RequestBody LoginCredentials credentials){
        if(!loginService.isLoggedIn()){
            LoginUserDto loginUserDto = loginService
                    .validateAndGetUser(credentials.getEmail(), credentials.getUsername());
            if(loginService.attemptLogin(credentials.getRawPassword(), loginUserDto)){
                logger.info("Logged in as: " + credentials.getUsername());
                return new ResponseEntity<>(loginUserDto, HttpStatus.ACCEPTED);
            }else {
                logger.severe("Login failed with credentials:" + credentials.getUsername() + " " + credentials.getEmail());
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        logger.info("Login attempt while logged in");
        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("logout")
    @ResponseBody
    public ResponseEntity<Boolean> logout(@RequestBody LoginCredentials credentials){
        if(loginService.isLoggedIn()){
            LoginUserDto loginUserDto = loginService
                    .validateAndGetUser(credentials.getEmail(), credentials.getUsername());
            if(loginUserDto != null){
                loginService.logOut();
                logger.info("User: " + credentials.getUsername() + " logged out.");
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            logger.severe("Logout attempt with faulty credentials: " + credentials.getUsername() + " " + credentials.getEmail());
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        logger.severe("Logout attempt while not logged in!");
        return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("user/create")
    @ResponseBody
    public ResponseEntity<LoginUserDto> createUser(@RequestBody LoginCredentials credentials){
        if(!loginService.isLoggedIn()){
            try{
                LoginUserDto dto = loginService.createNewUser(credentials);
                if(dto != null){
                    logger.info("User created: " + credentials.getUsername() + " " + credentials.getEmail());
                    return new ResponseEntity<>(dto , HttpStatus.OK);
                }else {
                    logger.severe("Cant create user: " + credentials.getUsername() + " " + credentials.getEmail());
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }catch (Throwable err){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
          return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
