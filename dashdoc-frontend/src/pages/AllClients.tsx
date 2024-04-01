import { DataTable } from "@components/shared/tables/DataTable";
import React from "react";
import { CLIENT_TABLE_FIELDS } from "@constants";
import { ClientSearch } from "@components/ui/clients/ClientSearch";
import { useClient } from "@hooks/useClient";
import Button from "@components/shared/buttons/Button";
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
      <div className="flex-row-between align-center">
        <ClientSearch />
        <Button variant="contained" color="success" size="medium">
          <span>Add Client</span>
        </Button>
      </div>
      <DataTable columns={CLIENT_TABLE_FIELDS} rows={clients} noRowsLabel="No Clients (yet)" />
    </>
  );
};

export default AllClients;
