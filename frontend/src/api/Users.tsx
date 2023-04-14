import { api } from "./index";

export const GetUser = async (id: string) => {
  try {
    const url = `/api/users/${id}`;
    const response = await api.get(url);
    console.log(response.data);
    return response.data;
  } catch (err) {
    console.log(err);
  }
};
