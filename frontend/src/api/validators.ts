import { z } from "zod";

export const recipeCard = z.object({
  id: z.number(),
  recipeName: z.string(),
  image: z.string().nullable(),
});

export const recipeCategory = z.object({
  id: z.number(),
  name: z.string(),
});
