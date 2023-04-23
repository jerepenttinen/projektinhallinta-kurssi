import { api } from "./index";
import { user } from "./validators";

export const GetUser = async (id: string) => {
  const response = await api.get(`/api/users/${id}`);
  return user.parse(response.data);
};
