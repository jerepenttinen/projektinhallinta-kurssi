package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginUserDto {

    public LoginUserDto(LoginUserDao dao){
        this.id = dao.getId();
        this.username = dao.getUsername();
        this.password = dao.getPassword();
        this.profileId = dao.getProfileId();
        this.email = dao.getEmail();
    }

    private final Long id;
    private final String username;
    private final String password;
    private final Long profileId;
    private final String email;
}
