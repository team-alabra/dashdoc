import { Client } from "@typings/client";
import { get } from "@utils/http"

export const getAllClients = async (): Promise<Client[]> => {
  return await get("/api/user/clients");
}

export const getSingleClient = async (id: number): Promise<Client> => {
  return await get(`/api/client/${id}`);
}