package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ingredient") @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDao extends AbstractClass implements DaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_seq")
    @SequenceGenerator(name = "ingredient_seq", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "ingredient_name")
    private String name;
}
