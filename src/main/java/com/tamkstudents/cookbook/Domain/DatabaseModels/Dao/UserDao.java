package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.login.LoginCredentials;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="usr") @Getter @Setter @NoArgsConstructor
public class UserDao  extends AbstractClass implements DaoEntity {

    public UserDao(UserDto user){
        this.userName = user.getUserName();
        this.createdAt = user.getCreatedAt();
    }

    public UserDao(LoginCredentials credentials){
        java.util.Date utilDate = new java.util.Date();
        this.setUserName(credentials.getUsername());
        this.setCreatedAt(new Date(utilDate.getTime()));
        this.setRecipes(new HashSet<>());
    }

    @Id
    @GeneratedValue
    private Long user_id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "creator")
    private Set<RecipeDao> recipes;
}
