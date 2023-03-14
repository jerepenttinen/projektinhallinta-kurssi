package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.DtoEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserDto implements DtoEntity {

    public UserDto(UserDao dao) {
        this.userId = dao.getId();
        this.userName = dao.getUserName();
        this.createdAt = dao.getCreatedAt();
        this.recipeIds = new ArrayList<>();

        dao.getRecipes().forEach(recipe -> this.recipeIds.add(recipe.getId()));
    }

    private long userId;
    private String userName;
    private Timestamp createdAt;
    private List<Long> recipeIds;


}
