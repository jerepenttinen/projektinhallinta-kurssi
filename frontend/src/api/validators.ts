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

export const newRecipe = z.object({
  recipeName: z.string(),
  images: z.array(z.string()),
  instructions: z.array(z.string()),
  ingredients: z.array(
    z.object({
      ingredient: z.string(),
      quantity: z.string(),
    }),
  ),
  categories: z.array(z.string()),
});

export const recipePage = z.object({
  id: z.number(),
  recipeName: z.string(),
  creatorId: z.number(),
  images: z.array(z.string()),
  instructions: z.array(z.string()),
  ingredients: z.array(
    z.object({
      id: z.number(),
      ingredient: z.string(),
      quantity: z.string(),
    }),
  ),
  categories: z.array(z.string()),
});

export const recipeReview = z.object({
  id: z.number(),
  userId: z.number(),
  createdAt: z.coerce.date(),
  content: z.string(),
});

export const user = z.object({
  id: z.number(),
  username: z.string(),
  image: z.string(),
  description: z.string(),
});
