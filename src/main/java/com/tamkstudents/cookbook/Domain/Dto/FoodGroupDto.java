package com.tamkstudents.cookbook.Domain.Dto;

import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodGroupDto {

    public FoodGroupDto(FoodGroupDao dao){
        this.id = dao.getId();
        this.name = dao.getName();
    }

    private long id;
    private String name;
}
