package com.tamkstudents.cookbook.Domain.Dto;

import com.tamkstudents.cookbook.Domain.Dao.IngredientDao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IngredientDto {
    public IngredientDto(IngredientDao dao) {
        this.id = dao.getId();
        this.name = dao.getName();
    }

    private long id;
    private String name;
}
