package com.tamkstudents.cookbook.Domain.Dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "recipe_collection") @Getter @Setter
public class RecipeCollectionDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_collection_seq")
    @SequenceGenerator(name = "recipe_collection_seq", sequenceName = "recipe_collection_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "recipe_collection_name")
    private String collectionName;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserDao creator;

    @ManyToMany(fetch = FetchType.EAGER)
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
