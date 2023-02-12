package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="usr") @Getter @Setter @NoArgsConstructor
public class UserDao  extends AbstractClass implements DaoEntity {

    UserDao(UserDto user, List<RecipeDto> recipes){
        this.userName = user.getUserName();
        this.createdAt = user.getCreatedAt();
        this.recipes =  (HashSet) recipes;

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
