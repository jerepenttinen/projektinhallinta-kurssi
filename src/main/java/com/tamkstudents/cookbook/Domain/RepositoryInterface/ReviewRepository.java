package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.ReviewDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewDao, Long> {
    List<ReviewDao> findAllByRecipeId(long recipeId);
    List<ReviewDao> findAllByReviewerId(long reviewerId);
    Boolean existsReviewDaoByReviewerIdAndRecipeId(long userId, long recipeId);
}
