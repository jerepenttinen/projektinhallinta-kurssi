package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Request.CreateReviewRequest;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.Dto.ReviewDto;
import com.tamkstudents.cookbook.Service.Exceptions.RecipeNotFoundException;
import com.tamkstudents.cookbook.Service.Exceptions.ReviewExistsForRecipeByUserException;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import com.tamkstudents.cookbook.Service.ReviewService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reviews for recipe"),
            @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content),
    })
    @GetMapping("/{recipeId}")
    public ResponseEntity<List<ReviewDto>> getRecipeReviews(@PathVariable(name = "recipeId") Long recipeId) {
        try {
            return new ResponseEntity<>(reviewService.getRecipeReviews(recipeId), HttpStatus.OK);
        } catch (RecipeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Create recipe"),
            @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
            @ApiResponse(responseCode = "403", description = "You have already reviewed this recipe", content = @Content),
            @ApiResponse(responseCode = "404", description = "Recipe or user profile not found", content = @Content),
    })
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{recipeId}")
    public ResponseEntity<?> createReview(@PathVariable(name = "recipeId") Long recipeId, @Valid @RequestBody CreateReviewRequest createReviewRequest, @Parameter(hidden = true) LoginUserDao loginUserDao) {
        if (loginUserDao == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        try {
            reviewService.addReviewForRecipe(recipeId, loginUserDao.getProfileId(), createReviewRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ReviewExistsForRecipeByUserException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You have already reviewed this recipe");
        } catch (RecipeNotFoundException | UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{recipeId}/{reviewId}")
    @ResponseBody
    public ResponseEntity<List<ReviewDto>> editReview(
            @PathVariable(name = "recipeId") Integer recipeId,
            @PathVariable(name = "reviewId") Integer reviewId) {

        //TODO returns updated list of reviews of the recipe
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/{recipeId}/{reviewId}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteReview(
            @PathVariable(name = "recipeId") Integer recipeId,
            @PathVariable(name = "reviewId") Integer reviewId) {

        //TODO return true if all went correctly
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
