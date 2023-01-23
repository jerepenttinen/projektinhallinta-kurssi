package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.RecipeCollectionDao;
import com.tamkstudents.cookbook.Domain.DtoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RecipeCollectionDto implements DtoEntity {

    //constructor
    public RecipeCollectionDto(RecipeCollectionDao dao){
        this.id = dao.getId();
        this.collectionName = dao.getCollectionName();
        this.creatorId = dao.getCreator().getUser_id();
        this.recipeIds = new ArrayList<>();

        dao.getRecipes().forEach(recipeDao-> this.recipeIds.add(recipeDao.getId()));
    }

    private int id;
    private String collectionName;
    private int creatorId;
    private List<Integer> recipeIds;
}
