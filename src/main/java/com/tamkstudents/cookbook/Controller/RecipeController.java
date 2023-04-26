package com.tamkstudents.cookbook.Controller;

import com.tamkstudents.cookbook.Controller.Mapper.RecipeMapperService;
import com.tamkstudents.cookbook.Controller.Reply.CreateRecipeReply;
import com.tamkstudents.cookbook.Controller.Reply.GetCategoriesReply;
import com.tamkstudents.cookbook.Controller.Reply.RecipeCardReply;
import com.tamkstudents.cookbook.Controller.Reply.RecipeReply;
import com.tamkstudents.cookbook.Controller.Request.CreateRecipeRequest;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Service.Exceptions.RecipeNotFoundException;
import com.tamkstudents.cookbook.Service.Exceptions.UnknownFoodGroupException;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import com.tamkstudents.cookbook.Service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<RecipeCardReply>> getRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes().stream().map(recipeMapperService::recipeCardReplyFromRecipeDao).toList());
    }

    @Operation(description = "Images have to be base64 encoded. Categories given here have to be retrieved from /api/categories (TODO).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Recipe created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateRecipeReply.class))),
            @ApiResponse(responseCode = "400", description = "Request body validation failed or a given category is unknown", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not signed in", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected", content = @Content),
    })
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<CreateRecipeReply> postNewRecipe(@Valid @RequestBody CreateRecipeRequest createRecipeRequest, @Parameter(hidden = true) LoginUserDao loginUserDao) {
        if (loginUserDao == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        try {
            var recipe = recipeService.createRecipe(createRecipeRequest, userRepository.findById(loginUserDao.getProfileId()).orElseThrow());
            return ResponseEntity.ok(recipeMapperService.createRecipeReplyFromRecipeDao(recipe));
        } catch (UnknownFoodGroupException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Tuntematon kategoria: %s", e.getFoodGroup()));
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reseptiä ei löytynyt", e);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RecipeCardReply>> getRecipesByUserId(@PathVariable Long id) {
        try {
            var recipes = recipeService.getUserRecipes(id);
            return ResponseEntity.ok(recipes.stream().map(recipeMapperService::recipeCardReplyFromRecipeDao).toList());
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Käyttäjää ei löytynyt", e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeCardReply>> getRecipesBySearchTerm(@RequestParam String q) {
        var recipes = recipeService.search(q);
        return ResponseEntity.ok(recipes.stream().map(recipeMapperService::recipeCardReplyFromRecipeDao).toList());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<GetCategoriesReply>> getCategories() {
        return ResponseEntity.ok(recipeService.getRecipeCategories().stream().map(recipeMapperService::getCategoriesReplyFromFoodGroupDao).toList());
    }
}
