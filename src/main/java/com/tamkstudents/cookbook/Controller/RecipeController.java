package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Service.RecipeService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@Slf4j
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getRecipes() {
        List<RecipeDto> recipes;
        try {
            recipes = recipeService.getAllRecipes();
        } catch (Throwable err) {
            log.error("Unable to fetch all recipes");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto, @Parameter(hidden = true) LoginUserDao loginUserDao) {
        try {
            log.info("User: {}", loginUserDao.getId());
            var result = recipeService.createRecipe(recipeDto, userRepository.findById(loginUserDao.getProfileId()).orElseThrow());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Throwable err) {
            log.error("Unable to create recipe!");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> changesToRecipe(@RequestBody RecipeDto dto) {
        RecipeDto returnDto = recipeService.modifyRecipeById(dto);
        if (returnDto != null) {
            return new ResponseEntity<>(returnDto, HttpStatus.OK);
        } else {
            log.error("Recipe modify failed!");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable String id) {
        RecipeDto dto;
        try {
            dto = recipeService.getRecipeById(Long.valueOf(id));
        } catch (Throwable err) {
            log.warn("Recipe not found by id: {}", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RecipeDto>> getRecipesByUserId(@PathVariable String id) {
        List<RecipeDto> recipes = recipeService.getUserRecipes(Long.valueOf(id));
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }


}
