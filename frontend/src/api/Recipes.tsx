import { api } from "./index";
import { PostRecipeType } from "../Types";

export const GetRecipes = async () => {
  const url = "/api/recipes";
  try {
    const response = await api.get(url);
    if (response.data) return response.data;
  } catch (err) {
    console.log(err);
  }
};

export const PostRecipe = async (newRecipe: PostRecipeType) => {
  // Ei toimi, error code 400?
  console.log(newRecipe);
  try {
    const url = "/api/recipes";
    const response = await api.post(url, newRecipe);
    console.log(response);
  } catch (err) {
    console.log(err);
  }
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
  try {
    const url = "/api/recipes/" + id;
    const response = await api.get(url);
    if (response.data) return response.data;
  } catch (err) {
    console.log(err);
  }
};
