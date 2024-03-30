import { Client } from "@typings/client";
import { get, post, put } from "@utils/http";

export const getAll = async (): Promise<Client[]> => {
  return await get("/api/user/clients");
};

export const getById = async (id: number): Promise<Client> => {
  return await get(`/api/client/${id}`);
};

export const update = async (client: Client): Promise<Client> => {
  return await put("/api/client/update", client);
};

export const addNew = async (client: Client): Promise<Client> => {
  return await post("/api/client/save", client);
};
