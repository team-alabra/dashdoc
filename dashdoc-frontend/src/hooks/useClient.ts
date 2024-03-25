import { useEffect, useState } from "react";
import { getAllClients, getSingleClient } from "@api/client";
import { clientActions } from "@services/slices/clientSlice";
import { useDispatch, useSelector } from "./hooks";
import { Client } from "@typings/client";

export const useClient = () => {
  const dispatch = useDispatch();
  const clients: Client[] = useSelector((state) => state.clients);
  const singleClient: Client = useSelector((state) => state.clients);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  async function fetchProviderClients() {
    try {
      setIsLoading(true);
      const result = await getAllClients();
      dispatch(clientActions.setMany(result));
      setIsLoading(false);

    } catch (error) {
      console.error(error);
      setIsLoading(false);
    }
  };

  async function fetchSingleClient (id: number) {
    try {
      setIsLoading(true);
      const result = await getSingleClient(id);
      dispatch(clientActions.setOne(result));
      setIsLoading(false);

    } catch (error) {
      console.error(error);
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchProviderClients();
  }, []);

  return {
    clients,
    singleClient,
    fetchSingleClient,
    isLoading,
  };
};
