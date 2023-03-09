package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;


import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.FoodGroupDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "food_group") @Getter @Setter
public class FoodGroupDao extends AbstractClass implements DaoEntity {

    public FoodGroupDao(FoodGroupDto dto){
        this.id = dto.getId();
        this.name = dto.getName();
    }

    @Id
//    @GeneratedValue
    private Long id;

    @Column(name = "food_group_name")
    private String name;
}
