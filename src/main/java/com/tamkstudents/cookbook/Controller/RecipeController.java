package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Mapper.RecipeMapperService;
import com.tamkstudents.cookbook.Controller.Reply.RecipeReply;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Service.Exceptions.RecipeNotFoundException;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import com.tamkstudents.cookbook.Service.RecipeService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@Slf4j
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final UserRepository userRepository;
    private final RecipeMapperService recipeMapperService;

    @GetMapping
    public ResponseEntity<List<RecipeReply>> getRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes().stream().map(recipeMapperService::recipeReplyFromRecipeDao).toList());
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
    public ResponseEntity<RecipeReply> getRecipeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(recipeMapperService.recipeReplyFromRecipeDao(recipeService.getRecipeById(id)));
        } catch (RecipeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found", e);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RecipeReply>> getRecipesByUserId(@PathVariable Long id) {
        try {
            var recipes = recipeService.getUserRecipes(id);
            return ResponseEntity.ok(recipes.stream().map(recipeMapperService::recipeReplyFromRecipeDao).toList());
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }
}
