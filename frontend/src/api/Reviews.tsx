import { api } from "./index";

export const GetReviewByRecipeId = async (id: string) => {
  const url = "/api/reviews/" + id;
  try {
    const response = await api.get(url);
    return response.data;
  } catch (err) {
    console.log(err);
  }
};
