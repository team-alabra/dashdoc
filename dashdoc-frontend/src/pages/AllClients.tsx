import { DataTable } from "@components/shared/tables/DataTable";
import React from "react";
import { CLIENT_TABLE_FIELDS } from "@constants";
import { ClientSearch } from "@components/ui/clients/ClientSearch";
const AllClients = () => {
  return (
    <>
      <div>My Clients</div>
      <ClientSearch />
      <DataTable columns={CLIENT_TABLE_FIELDS} rows={[]} />
    </>
  );
};

export default AllClients;
