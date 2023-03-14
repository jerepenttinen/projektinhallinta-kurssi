package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import com.tamkstudents.cookbook.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/recipes")
public class RecipeController extends AbstractController{

    Logger logger = Logger.getLogger(RecipeController.class.getName());

    @Autowired
    RecipeService recipeService;

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<RecipeDto>> getRecipes() {
        List<RecipeDto> recipes;
        try {
            recipes = recipeService.getAllRecipes();
        } catch (Throwable err){
            logger.severe("Unable to fetch all recipes");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto dto) {
        //luodaan tietokantaan uusi objekti
        RecipeDto returnDto;
        try {
             returnDto = recipeService.createRecipe(dto);
        } catch (Throwable err) {
            logger.severe("Unable to create recipe!");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //palautetaan luotu objekti
        return new ResponseEntity<>(returnDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RecipeDto> changesToRecipe(@RequestBody RecipeDto dto){
        RecipeDto returnDto = recipeService.modifyRecipeById(dto);
        if(returnDto != null){
            return new ResponseEntity<>(returnDto, HttpStatus.OK);
        }else {
            logger.severe("Recipe modify failed!");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable String id){
        RecipeDto dto;
        try {
           dto = recipeService.getRecipeById(Long.valueOf(id));
        } catch (Throwable err) {
            logger.warning("Recipe not found by id: " + id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<List<RecipeDto>> getRecipesByUserId(@PathVariable String id){
        List<RecipeDto> recipes = recipeService.getUserRecipes(Long.valueOf(id));
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }


}
