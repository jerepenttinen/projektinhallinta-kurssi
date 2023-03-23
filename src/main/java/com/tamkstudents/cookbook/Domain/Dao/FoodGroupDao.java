package com.tamkstudents.cookbook.Domain.Dao;


import com.tamkstudents.cookbook.Domain.Dto.FoodGroupDto;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "food_group") @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodGroupDao {

    public FoodGroupDao(FoodGroupDto dto){
        this.id = dto.getId();
        this.name = dto.getName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_group_seq")
    @SequenceGenerator(name = "food_group_seq", sequenceName = "food_group_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "food_group_name")
    private String name;
}
