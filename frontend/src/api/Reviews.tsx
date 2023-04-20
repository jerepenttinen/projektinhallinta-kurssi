import { z } from "zod";
import { api } from "./index";
import { review } from "./validators";

export const GetReviewByRecipeId = async (id: string) => {
  const response = await api.get(`/api/reviews/${id}`);
  return z.array(review).parse(response.data);
};
