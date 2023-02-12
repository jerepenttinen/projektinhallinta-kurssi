package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import com.tamkstudents.cookbook.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController extends AbstractController{

    @Autowired
    RecipeService recipeService;

    @ResponseBody
    public ResponseEntity<List<RecipeDto>> getRecipes() {
        List<RecipeDto> recipes = recipeService.getAllRecipes();

        //returns all recipes
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto dto) {

        //TODO returns created recipe
        return new ResponseEntity<>(new RecipeDto(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RecipeDto> changesToRecipe(@RequestBody RecipeDto dto){

        //TODO returns altered recipe
        return new ResponseEntity<>(new RecipeDto(), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable String id){

        //TODO returns specific recipe
        return new ResponseEntity<>(new RecipeDto(), HttpStatus.OK);
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<List<RecipeDto>> getRecipesByUserId(@PathVariable String id){

        //TODO returns users recipes
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }


}
