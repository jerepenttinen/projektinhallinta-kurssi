package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="usr") @Getter @Setter
public class UserDao  extends AbstractClass implements DaoEntity {
    @Id
    @GeneratedValue
    private int user_id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "creator")
    private Set<RecipeDao> recipes;
}
