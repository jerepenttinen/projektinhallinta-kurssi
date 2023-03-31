package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Controller.Mapper.ReviewMapperService;
import com.tamkstudents.cookbook.Controller.Request.CreateReviewRequest;
import com.tamkstudents.cookbook.Domain.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.Dao.ReviewDao;
import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.Dto.ReviewDto;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.RecipeRepository;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.ReviewRepository;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Service.Exceptions.RecipeNotFoundException;
import com.tamkstudents.cookbook.Service.Exceptions.ReviewExistsForRecipeByUserException;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ReviewMapperService reviewMapperService;
    private final EntityManager entityManager;

    public List<ReviewDto> getRecipeReviews(long recipeId) throws RecipeNotFoundException {
        try {
            return reviewRepository.findAllByRecipeId(recipeId).stream().map(reviewMapperService::reviewDtoFromReviewDao).toList();
        } catch (Throwable e) {
            throw new RecipeNotFoundException();
        }
    }

    @Transactional
    public Long addReviewForRecipe(long recipeId, long userId, CreateReviewRequest createReviewRequest) throws ReviewExistsForRecipeByUserException, RecipeNotFoundException, UserNotFoundException {
        if (reviewRepository.existsReviewDaoByReviewerIdAndRecipeId(userId, recipeId)) {
            throw new ReviewExistsForRecipeByUserException();
        }
        RecipeDao recipe;
        try {
            recipe = recipeRepository.getReferenceById(recipeId);
        } catch (EntityNotFoundException e) {
            throw new RecipeNotFoundException();
        }

        UserDao user;
        try {
            user = userRepository.getReferenceById(userId);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException();
        }

        var reviewDao = ReviewDao.builder()
                .recipe(recipe)
                .reviewer(user)
                .isUpvote(createReviewRequest.getIsUpvote())
                .content(createReviewRequest.getContent())
                .build();

        entityManager.persist(reviewDao);

        return reviewDao.getId();
    }
}
