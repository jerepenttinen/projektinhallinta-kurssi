package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "recipe_collection") @Getter @Setter
public class RecipeCollectionDao extends AbstractClass implements DaoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "recipe_collection_name")
    private String collectionName;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserDao creator;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "collection_has_recipes",
            joinColumns = {
                    @JoinColumn(name = "collection_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "recipe_id")
            }
    )
    private Set<RecipeDao> recipes = new HashSet<>();

}
