package com.tamkstudents.cookbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamkstudents.cookbook.Controller.Request.CreateRecipeRequest;
import com.tamkstudents.cookbook.Controller.Request.Dto.IngredientWithQuantityRequest;
import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Service.LoginService;
import lombok.SneakyThrows;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test-containers")
public class RecipeTests {
    private final Faker faker = new Faker();
    @Autowired
    private LoginService loginService;
    @Autowired
    private MockMvc mvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private LoginUserDao createLoginUser(SignUpRequest signUpRequest) {
        return loginService.createNewUser(
                signUpRequest
        );
    }

    @Test
    public void createNewRecipe() throws Exception {
        var signUpRequest = fakeDataSignUpRequest();
        var loginUser = createLoginUser(signUpRequest);
        // 1px * 1px png image
        String recipe_image = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAABHNCSVQICAgIfAhkiAAAAAtJREFUCJlj+A8EAAn7A/3jVfKcAAAAAElFTkSuQmCC";

        var createRecipeRequest = new CreateRecipeRequest(
                faker.food().dish(), // Recipe name
                List.of(recipe_image), // Images
                List.of(faker.famousLastWords().lastWords(), faker.famousLastWords().lastWords(), faker.famousLastWords().lastWords()), // Instructions
                List.of(new IngredientWithQuantityRequest[]{
                        new IngredientWithQuantityRequest(faker.food().ingredient(), faker.food().measurement()),
                        new IngredientWithQuantityRequest(faker.food().ingredient(), faker.food().measurement()),
                        new IngredientWithQuantityRequest(faker.food().spice(), faker.food().measurement()),
                }),
                List.of() // Categories
        );

        var result = mvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createRecipeRequest))
                        .with(user(loginUser))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").exists()
                )
                .andDo(print())
                .andReturn();

        JsonNode jsonNode = new ObjectMapper().readTree(result.getResponse().getContentAsString());
        var recipeId = jsonNode.get("id").asLong();

        mvc.perform(get("/api/recipes/" + recipeId))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(recipeId),
                        jsonPath("$.creatorId").value(loginUser.getProfileId()),
                        jsonPath("$.recipeName").value(createRecipeRequest.getRecipeName()),
                        jsonPath("$.images[0]").value(recipe_image)
                )
                .andDo(print());
    }

    @Test
    public void postNewRecipeWithUnknownCategory() throws Exception {
        var signUpRequest = fakeDataSignUpRequest();
        var loginUser = createLoginUser(signUpRequest);

        var createRecipeRequest = new CreateRecipeRequest(
                faker.food().dish(), // Recipe name
                List.of(), // Images
                List.of(), // Instructions
                List.of(), // Ingredients
                List.of(faker.food().vegetable()) // Categories
        );

        mvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createRecipeRequest))
                        .with(user(loginUser))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    private SignUpRequest fakeDataSignUpRequest() {
        return new SignUpRequest(
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(8, 30)
        );
    }
}
