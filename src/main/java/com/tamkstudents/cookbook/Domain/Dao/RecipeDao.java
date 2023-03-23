package com.tamkstudents.cookbook.Domain.Dao;

import com.tamkstudents.cookbook.Domain.Dto.RecipeDto;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "recipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
    @SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private UserDao creator;

    @Type(ListArrayType.class)
    @Column(name = "recipe_instruction", nullable = false)
    private List<String> instruction;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_has_image",
            joinColumns = {
                    @JoinColumn(name = "recipe_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            }
    )
    private Set<ImageDao> images = new HashSet<>();

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RecipeHasIngredientDao> ingredients = new ArrayList<>();

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

    public void addIngredient(IngredientDao ingredient, String quantity) {
        var recipeHasIngredient = new RecipeHasIngredientDao(this, ingredient, quantity);
        ingredients.add(recipeHasIngredient);
    }

//    public void modify(RecipeDto dto, UserDao user) {
//        this.recipeName = dto.getRecipeName();
//        this.creator = user;
//        this.instruction = dto.getInstruction();
//        this.ingredients = (HashSet) dto.getIngredients();
//        this.foodGroups = (HashSet) dto.getFoodGroups();
//    }
}
