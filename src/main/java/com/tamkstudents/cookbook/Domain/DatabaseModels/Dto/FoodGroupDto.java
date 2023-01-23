package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.DtoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FoodGroupDto implements DtoEntity {

    public FoodGroupDto(FoodGroupDao dao){
        this.id = dao.getId();
        this.name = dao.getName();
    }

    private int id;
    private String name;
}
