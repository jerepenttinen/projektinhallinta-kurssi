import { z } from "zod";
import { api } from "./index";
import { user, updateUserDetails } from "./validators";

export const GetUser = async (id: string) => {
  const response = await api.get(`/api/users/${id}`);
  return user.parse(response.data);
};


export const UpdateUserDetails = async (
  newDetails: z.infer<typeof updateUserDetails>,
) => {
  await api.put(`/api/users/${newDetails.id}`, {
    description: newDetails.description,
    image: newDetails.image,
  });
};

export const GetMultipleUsers = async (ids: number[]) => {
  const response = await api.post("/api/users", ids);
  const result = z.array(user).parse(response.data);
  return result.reduce<Record<number, z.infer<typeof user>>>((acc, cur) => {
    acc[cur.id] = cur;
    return acc;
  }, {});
};
