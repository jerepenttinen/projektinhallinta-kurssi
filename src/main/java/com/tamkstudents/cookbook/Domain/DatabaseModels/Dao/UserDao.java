package com.tamkstudents.cookbook.Domain.DatabaseModels.Dao;

import com.tamkstudents.cookbook.Domain.AbstractClass;
import com.tamkstudents.cookbook.Domain.DaoEntity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="usr") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class UserDao  extends AbstractClass implements DaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_seq")
    @SequenceGenerator(name = "usr_seq", sequenceName = "usr_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "creator")
    private Set<RecipeDao> recipes;
}
