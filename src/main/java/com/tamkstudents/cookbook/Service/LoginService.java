package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.LoginUserRepository;
import com.tamkstudents.cookbook.Domain.login.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;
import java.util.logging.Logger;

@Service @SessionScope
public class LoginService {
    @Autowired
    PasswordService passwordService;

    @Autowired
    LoginUserRepository loginUserRepository;

    @Autowired
    UserService userService;

    Logger logger = Logger.getLogger(LoginService.class.getName());

    private boolean loggedIn = false;
    private LoginUserDto loggedUser = null;

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public boolean attemptLogin(CharSequence rawPassword ,LoginUserDto loginUser) {
        if(!isLoggedIn() && passwordService.comparePassword(rawPassword, loginUser.getPassword())){
            loggedIn = true;
            loggedUser = loginUser;
            return true;
        }
        if(isLoggedIn()){
            logger.severe("Login attempt while logged in");
        }
        if(passwordService.comparePassword(rawPassword, loginUser.getPassword())){
            logger.severe("Password invalid");
        }
        return false;
    }

    public void logOut(){
        if(loggedIn || loggedUser != null){
            loggedIn = false;
            loggedUser = null;
        }
    }

    public LoginUserDto validateAndGetUser(String email, String username){
        Optional<LoginUserDao> foundUser = loginUserRepository.findByEmailOrUsername(email, username);
        return foundUser.map(LoginUserDto::new).orElse(null);
    }

    public LoginUserDto createNewUser(LoginCredentials credentials){
        Optional<LoginUserDao> checker = loginUserRepository
                .findByEmailOrUsername(credentials.getEmail(), credentials.getUsername());
        if(checker.isEmpty()){
            String encryptPassword = passwordService.encrypt(credentials.getRawPassword());
            UserDto dto = userService.saveNewProfile(credentials);
            LoginUserDao newUserDao = new LoginUserDao(credentials, encryptPassword, dto.getUserId());
            loginUserRepository.save(newUserDao);
            try {
                Optional<LoginUserDao> dao = loginUserRepository.findByEmailOrUsername(credentials.getEmail(), credentials.getUsername());
                LoginUserDto newUserDto = null;
                if(dao.isPresent()){
                    newUserDto = new LoginUserDto(dao.get());
                }
                logger.info("User creation succesfull: " +
                        newUserDto.getUsername()+" "+
                        newUserDto.getEmail()+" profileId: "+
                        newUserDto.getProfileId()
                        );
                return newUserDto;
            } catch (Throwable err){
                logger.severe("User not found after creation");
                return null;
            }
        } else {
            logger.warning("User already existing");
            return null;
        }
    }
}
