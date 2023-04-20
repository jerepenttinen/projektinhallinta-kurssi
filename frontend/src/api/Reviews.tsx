import { z } from "zod";
import { api } from "./index";
import { recipeReview } from "./validators";

export const GetReviewByRecipeId = async (id: string) => {
  const response = await api.get(`/api/reviews/${id}`);
  return z.array(recipeReview).parse(response.data);
};
