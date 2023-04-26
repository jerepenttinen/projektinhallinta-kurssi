import { api } from "./index";
import { z } from "zod";
import {
  recipeCard,
  recipeCategory,
  newRecipe,
  recipePage,
} from "./validators";

export const getRecipes = async () => {
  const response = await api.get("/api/recipes");
  return z.array(recipeCard).parse(response.data);
};

export const getCategories = async () => {
  const response = await api.get("/api/recipes/categories");
  const data = z.array(recipeCategory).parse(response.data);
  return data.map((category) => category.name);
};

export const postNewRecipe = async (recipe: z.infer<typeof newRecipe>) => {
  const response = await api.post("/api/recipes", recipe);
  return z
    .object({
      id: z.number(),
    })
    .parse(response.data);
};

export const GetUserRecipes = async (uid: string) => {
  try {
    const url = `/api/recipes/user/${uid}`;
    const response = await api.get(url);
    console.log(response.data);
    return response.data;
  } catch (err) {
    console.log(err);
  }
};

export const GetRecipeById = async (id: string) => {
  const response = await api.get(`/api/recipes/${id}`);
  return recipePage.parse(response.data);
};

export const SearchRecipes = async (term: string) => {
  const params = new URLSearchParams([["q", term]]);
  const response = await api.get(`/api/recipes/search`, { params });
  return z.array(recipeCard).parse(response.data);
};
