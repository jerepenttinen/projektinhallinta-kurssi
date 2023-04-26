package com.tamkstudents.cookbook.Domain.Dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @SequenceGenerator(name = "review_seq", sequenceName = "review_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeDao recipe;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private UserDao reviewer;

    @Column(name = "is_upvote", nullable = false)
    private Boolean isUpvote;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
}
