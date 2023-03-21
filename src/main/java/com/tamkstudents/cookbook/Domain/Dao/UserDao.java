package com.tamkstudents.cookbook.Domain.Dao;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="usr") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class UserDao {
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
