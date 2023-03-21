package com.tamkstudents.cookbook.Domain.Dto;

import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FoodGroupDto {

    public FoodGroupDto(FoodGroupDao dao){
        this.id = dao.getId();
        this.name = dao.getName();
    }

    private long id;
    private String name;
}
