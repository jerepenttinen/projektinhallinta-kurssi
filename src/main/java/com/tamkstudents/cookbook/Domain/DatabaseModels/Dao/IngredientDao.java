package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ingredient") @Getter @Setter
public class IngredientDao extends AbstractClass implements DaoEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ingredient_name")
    private String name;
}
