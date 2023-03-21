package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Controller.Reply.CurrentUserReply;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
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
}
