package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Service @SessionScope
public class LoginService {
    @Autowired
    PasswordService passwordService;

    @Autowired
    LoginUserRepository loginUserRepository;

    private boolean loggedIn = false;
    private LoginUserDto loggedUser = null;

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public boolean attemptLogin(CharSequence rawPassword ,LoginUserDto loginUser) {
        if(passwordService.comparePassword(rawPassword, loginUser.getPassword())){
            loggedIn = true;
            loggedUser = loginUser;
            return true;
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
        if(foundUser.isPresent()){
            return new LoginUserDto(foundUser.get());
        }
        return null;
    }
}
