package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.login.SignupCredentials;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="usr") @Getter @Setter @NoArgsConstructor
public class UserDao  extends AbstractClass implements DaoEntity {

    public UserDao(UserDto user){
        this.userName = user.getUserName();
        this.createdAt = user.getCreatedAt();
    }

    public UserDao(SignupCredentials credentials){
        this.setUserName(credentials.getUsername());
        this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        this.setRecipes(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_seq")
    @SequenceGenerator(name = "usr_seq", sequenceName = "usr_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "creator")
    private Set<RecipeDao> recipes;
}
