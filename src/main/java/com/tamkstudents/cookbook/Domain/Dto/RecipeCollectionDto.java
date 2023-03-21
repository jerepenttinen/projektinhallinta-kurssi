package com.tamkstudents.cookbook.Domain.Dto;

import com.tamkstudents.cookbook.Domain.Dao.RecipeCollectionDao;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RecipeCollectionDto {
    public RecipeCollectionDto(RecipeCollectionDao dao){
        this.id = dao.getId();
        this.collectionName = dao.getCollectionName();
        this.creatorId = dao.getCreator().getId();
        this.recipeIds = new ArrayList<>();

        dao.getRecipes().forEach(recipeDao-> this.recipeIds.add(recipeDao.getId()));
    }

    private long id;
    private String collectionName;
    private long creatorId;
    private List<Long> recipeIds;
}
