import { DataTable } from "@components/shared/tables/DataTable";
import React from "react";
import { CLIENT_TABLE_FIELDS } from "@constants";
import { ClientSearch } from "@components/ui/clients/ClientSearch";
import { useClient } from "@hooks/useClient";
const AllClients = () => {
  const {
    clients,
    singleClient,
    fetchClient,
    isLoading,
    updateClient,
    addClient
  } = useClient();

  if (isLoading) 
  {
    return (
      <>
        ...loading
      </>
    )
  }
  
  return (
    <>
      <div>My Clients</div>
      <ClientSearch />
      <DataTable columns={CLIENT_TABLE_FIELDS} rows={clients} noRowsLabel="No Clients (yet)" />
    </>
  );
};

export default AllClients;
