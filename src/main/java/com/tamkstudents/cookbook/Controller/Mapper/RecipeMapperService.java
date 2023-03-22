package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Controller.Reply.CreateRecipeReply;
import com.tamkstudents.cookbook.Controller.Reply.RecipeReply;
import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.Dto.IngredientDto;
import com.tamkstudents.cookbook.Service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeMapperService {
    private final MediaService mediaService;

    public RecipeReply recipeReplyFromRecipeDao(RecipeDao recipeDao) {
        return RecipeReply.builder()
                .id(recipeDao.getId())
                .recipeName(recipeDao.getRecipeName())
                .creatorId(recipeDao.getCreator().getId())
                .image(mediaService.imgToBase64(recipeDao.getImage()))
                .instructions(recipeDao.getInstruction())
                .ingredients(recipeDao.getIngredients().stream().map(ingredient -> new IngredientDto(ingredient.getId(), ingredient.getName())).toList())
                .foodGroups(recipeDao.getFoodGroups().stream().map(FoodGroupDao::getName).toList() )
                .build();
    }

    public CreateRecipeReply createRecipeReplyFromRecipeDao(RecipeDao recipeDao) {
        return new CreateRecipeReply(recipeDao.getId());
    }
}
