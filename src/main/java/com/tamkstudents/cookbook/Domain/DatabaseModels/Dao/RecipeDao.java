package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity(name="recipe") @Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDao extends AbstractClass implements DaoEntity {
    public RecipeDao(RecipeDto dto, UserDao user) {
        this.recipeName = dto.getRecipeName();
        this.creator = user;
        this.instruction = dto.getInstruction();
        this.image = dto.getImage();
        this.ingredients =  dto.getIngredients().stream().map(d -> new IngredientDao(d.getId(), d.getName())).collect(Collectors.toSet());
        this.foodGroups =  dto.getFoodGroups().stream().map(f -> new FoodGroupDao(f.getId(), f.getName())).collect(Collectors.toSet());
    }

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

    @Column(name = "recipe_img")
    private byte[] image;

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

    public boolean modify(RecipeDto dto,UserDao user){
        this.recipeName = dto.getRecipeName();
        this.creator = user;
        this.instruction = dto.getInstruction();
        this.image = dto.getImage();
        this.ingredients = (HashSet) dto.getIngredients();
        this.foodGroups = (HashSet) dto.getFoodGroups();
        return true;
    }
}
