package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.DaoEntity;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
import com.tamkstudents.cookbook.Domain.login.SignupCredentials;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "login_user") @NoArgsConstructor @Getter @Setter
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

    public LoginUserDao(SignupCredentials credentials, String password, long profileId){
        this.setUsername(credentials.getUsername());
        this.setFirstName(credentials.getFirstname());
        this.setLastName(credentials.getLastname());
        this.setEmail(credentials.getEmail());
        this.setPassword(password);
        this.setProfileId(profileId);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_user_seq")
    @SequenceGenerator(name = "login_user_seq", sequenceName = "login_user_id_seq", allocationSize = 1)
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
