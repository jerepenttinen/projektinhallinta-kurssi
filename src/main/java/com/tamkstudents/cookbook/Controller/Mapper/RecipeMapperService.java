package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Controller.Reply.CreateRecipeReply;
import com.tamkstudents.cookbook.Controller.Reply.GetCategoriesReply;
import com.tamkstudents.cookbook.Controller.Reply.RecipeCardReply;
import com.tamkstudents.cookbook.Controller.Reply.RecipeReply;
import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.Dao.RecipeDao;
import com.tamkstudents.cookbook.Service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeMapperService {
    private final MediaService mediaService;
    private final IngredientMapperService ingredientMapperService;

    public RecipeReply recipeReplyFromRecipeDao(RecipeDao recipeDao) {
        return RecipeReply.builder()
                .id(recipeDao.getId())
                .recipeName(recipeDao.getRecipeName())
                .creatorId(recipeDao.getCreator().getId())
                .images(
                        recipeDao
                                .getImages()
                                .stream()
                                .map(imageDao -> mediaService
                                        .imageToBase64(imageDao.getImage())
                                )
                                .toList()
                )
                .instructions(recipeDao.getInstruction())
                .ingredients(
                        recipeDao
                                .getIngredients()
                                .stream()
                                .map(ingredientMapperService::ingredientWithQuantityReplyFromRecipeHasIngredientsDao)
                                .toList()
                )
                .categories(recipeDao.getFoodGroups().stream().map(FoodGroupDao::getName).toList())
                .build();
    }

    public RecipeCardReply recipeCardReplyFromRecipeDao(RecipeDao recipeDao) {
        var builder = RecipeCardReply.builder()
                .id(recipeDao.getId())
                .recipeName(recipeDao.getRecipeName());

        var image = recipeDao.getImages().stream().findFirst();
        image.ifPresent(imageDao -> builder.image(mediaService.imageToBase64(imageDao.getImage())));

        return builder.build();
    }

    public CreateRecipeReply createRecipeReplyFromRecipeDao(RecipeDao recipeDao) {
        return new CreateRecipeReply(recipeDao.getId());
    }

    public GetCategoriesReply getCategoriesReplyFromFoodGroupDao(FoodGroupDao foodGroupDao) {
        return GetCategoriesReply.builder()
                .id(foodGroupDao.getId())
                .name(foodGroupDao.getName())
                .build();
    }
}
