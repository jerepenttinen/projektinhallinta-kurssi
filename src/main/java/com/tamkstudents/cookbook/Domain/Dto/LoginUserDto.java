package com.tamkstudents.cookbook.Domain.Dto;

import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginUserDto {

    public LoginUserDto(LoginUserDao dao){
        this.id = dao.getId();
        this.username = dao.getLoginUsername();
        this.password = dao.getPassword();
        this.profileId = dao.getProfileId();
        this.email = dao.getEmail();
        this.firstName = dao.getFirstName();
        this.lastName = dao.getLastName();
    }

    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final Long profileId;
    private final String email;
}
