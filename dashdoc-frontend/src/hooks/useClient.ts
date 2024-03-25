import { useEffect, useState } from "react";
import * as clientApi from "@api/client";
import { clientActions } from "@services/slices/clientSlice";
import { useDispatch, useSelector } from "./hooks";
import { Client } from "@typings/client";

export const useClient = () => {
  const dispatch = useDispatch();
  const clients: Client[] = useSelector(clientActions.getMany);
  const singleClient: Client = useSelector(clientActions.getOne);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  async function fetchAllClients() {
    try {
      setIsLoading(true);
      const result = await clientApi.getAll();
      dispatch(clientActions.setMany(result));
      setIsLoading(false);

    } catch (error) {
      console.error(error);
      setIsLoading(false);
    }
  };

  async function fetchClient (id: number) {
    try {
      setIsLoading(true);
      const result = await clientApi.getById(id);
      dispatch(clientActions.setOne(result));
      setIsLoading(false);

    } catch (error) {
      console.error(error);
      setIsLoading(false);
    }
  };

  async function updateClient (client: Client) {
    try {
      setIsLoading(true);
      const result = await clientApi.update(client);
      dispatch(clientActions.setOne(result));
      setIsLoading(false);

    } catch (error) {
      console.error(error);
      setIsLoading(false);
    }
  };

  async function addClient (client: Client) {
    try {
      setIsLoading(true);
      const result = await clientApi.addNew(client);
      dispatch(clientActions.setOne(result));
      setIsLoading(false);

    } catch (error) {
      console.error(error);
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchAllClients();
  }, []);

  return {
    clients,
    singleClient,
    fetchClient,
    isLoading,
    updateClient,
    addClient
  };
};
