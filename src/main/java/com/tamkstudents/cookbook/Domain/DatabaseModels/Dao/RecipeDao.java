package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.RecipeRepository;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.UserRepository;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name="recipe") @Getter @Setter @NoArgsConstructor
public class RecipeDao extends AbstractClass implements DaoEntity {

    public RecipeDao(RecipeDto dto, UserDto user) {
        this.recipeName = dto.getRecipeName();
        this.creator = new UserDao(user, new ArrayList<>());
        this.instruction = dto.getInstruction();
        this.image = dto.getImage();
        this.ingredients = (HashSet) dto.getIngredients();
        this.foodGroups = (HashSet) dto.getFoodGroups();
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private UserDao creator;

    @Column(name = "recipe_instruction", nullable = false)
    @ElementCollection
    private List<String> instruction;

    @Column(name = "recipe_img")
    private String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_has_ingredients",
            joinColumns = {
                    @JoinColumn(name = "recipe_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ingredient_id")
            }
    )
    private Set<IngredientDao> ingredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_has_food_groups",
            joinColumns = {
                    @JoinColumn(name = "recipe_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "food_group_id")
            }
    )
    private Set<FoodGroupDao> foodGroups = new HashSet<>();


}
