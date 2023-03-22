package com.tamkstudents.cookbook.Domain.Dao;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ingredient") @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_seq")
    @SequenceGenerator(name = "ingredient_seq", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "ingredient_name")
    private String name;
}
