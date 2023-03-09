package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.DtoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodGroupDto implements DtoEntity {

    public FoodGroupDto(FoodGroupDao dao){
        this.id = dao.getId();
        this.name = dao.getName();
    }

    private long id;
    private String name;
}
