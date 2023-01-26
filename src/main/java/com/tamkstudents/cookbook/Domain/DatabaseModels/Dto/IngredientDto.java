package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.IngredientDao;
import com.tamkstudents.cookbook.Domain.DtoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class IngredientDto implements DtoEntity {

    public IngredientDto(IngredientDao dao) {
        this.id = dao.getId();
        this.name = dao.getName();
    }

    private int id;
    private String name;
}
