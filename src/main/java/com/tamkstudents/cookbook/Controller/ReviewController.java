package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.ReviewDto;
import com.tamkstudents.cookbook.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends AbstractController{

    @Autowired
    ReviewService reviewService;

    @RequestMapping("/{recipeId}")
    @ResponseBody
    public ResponseEntity<List<ReviewDto>> getRecipeReviews(@PathVariable(name = "recipeId") Integer id){
        //TODO get reviews on recipe
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping("/{recipeId}")
    @ResponseBody
    public ResponseEntity<List<ReviewDto>> createReview(
            @PathVariable(name ="recipeId") Integer id,
            @RequestParam ReviewDto dto) {

        //TODO returns updated list of reviews of the recipe
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PutMapping("/{recipeId}/{reviewId}")
    @ResponseBody
    public ResponseEntity<List<ReviewDto>> editReview
            (@PathVariable(name = "recipeId") Integer recipeId,
             @PathVariable(name = "reviewId") Integer reviewId){

        //TODO returns updated list of reviews of the recipe
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}/{reviewId}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteReview(
            @PathVariable(name = "recipeId") Integer recipeId,
            @PathVariable(name = "reviewId") Integer reviewId){

        //TODO return true if all went correctly
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
