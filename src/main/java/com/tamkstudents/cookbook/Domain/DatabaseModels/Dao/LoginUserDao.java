package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.DaoEntity;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
import com.tamkstudents.cookbook.Domain.login.LoginCredentials;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @NoArgsConstructor @Getter @Setter
public class LoginUserDao implements DaoEntity {

    public LoginUserDao(LoginUserDto dto){
        this.id = dto.getId();
        this.username = dto.getUsername();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.password = dto.getPassword();
        this.profileId = dto.getProfileId();
        this.email = dto.getEmail();
    }

    public LoginUserDao(LoginCredentials credentials, String password, long profileId){
        this.setUsername(credentials.getUsername());
        this.setEmail(credentials.getEmail());
        this.setPassword(password);
        this.setProfileId(profileId);
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "login_user_name")
    private String username;

    @Column(name = "login_user_password")
    private String password;

    @Column(name = "login_user_firstname")
    private String firstName;

    @Column(name= "login_user_lastname")
    private String lastName;

    @Column(name = "user_profile")
    private Long profileId;

    @Column(name = "user_email")
    private String email;
}
