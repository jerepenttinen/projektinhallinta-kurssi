export type RecipeType = {
  id: number;
  recipeName: string;
  creatorId: number;
  image: string;
  instructions: Array<string>;
  ingredients: Array<{
    id: number;
    name: string;
    quantity: string;
  }>;
  foodGroups: Array<string>;
};
export type ReviewType = {
  id: number;
  userId: number;
  created: Date;
  content: string;
};
export type UserType = {
  id: number;
  username: string;
  image: string;
  description: string;
};

export type PostRecipeType = {
  recipeName: string | undefined;
  images: string[] | undefined;
  instructions: string[] | undefined;
  ingredients: {
    ingredient: string | null;
    quantity: string | null;
  }[];

  categories: string[] | null;
};
