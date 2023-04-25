import { z } from "zod";
import { api } from "./index";
import { createRecipeReview, recipeReview } from "./validators";

export const GetReviewByRecipeId = async (id: string) => {
  const response = await api.get(`/api/reviews/${id}`);
  return z.array(recipeReview).parse(response.data);
};

export const CreateReviewForRecipe = async (
  review: z.infer<typeof createRecipeReview>,
) => {
  await api.post(`/api/reviews/${review.recipeId}`, {
    content: review.content,
    upvote: review.upvote,
  });
};
