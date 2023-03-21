package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Controller.Reply.CurrentUserReply;
import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class LoginMapperService {
    public CurrentUserReply loginUserDaoToCurrentUserReply(@NotNull final LoginUserDao loginUserDao) {
        return CurrentUserReply.builder()
                .id(loginUserDao.getId())
                .email(loginUserDao.getEmail())
                .username(loginUserDao.getLoginUsername())
                .firstName(loginUserDao.getFirstName())
                .lastName(loginUserDao.getLastName())
                .profileId(loginUserDao.getProfileId())
                .build();
    }

    public LoginUserDao createLoginUserDao(@NotNull final SignUpRequest signUpRequest, @NotNull final String encryptedPassword, @NotNull final UserDao userDao) {
        return LoginUserDao.builder()
                .loginUsername(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .firstName(signUpRequest.getFirstname())
                .lastName(signUpRequest.getLastname())
                .password(encryptedPassword)
                .profileId(userDao.getId())
                .build();


    }
}
