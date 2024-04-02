import { DataTable } from "@components/shared/tables/DataTable";
import React from "react";
import { CLIENT_TABLE_FIELDS } from "@constants";
import { useClient } from "@hooks/useClient";

const AllClients = () => {
  const {
    clients,
    isLoading,
  } = useClient();

  if (isLoading) 
  {
    return (
      <>
        {/* LOADER PLACEHOLDER */}
        ...loading
      </>
    )
  }

  return (
    <>
      <div>My Clients</div>
      <DataTable columns={CLIENT_TABLE_FIELDS} rows={clients} noRowsLabel="No Clients (yet)" />
    </>
  );
};

export default AllClients;
