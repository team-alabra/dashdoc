import { DataTable } from "@components/shared/tables/DataTable";
import React from "react";
import { CLIENT_TABLE_FIELDS } from "@constants";
const AllClients = () => {
  return (
    <>
      <div>My Clients</div>
      <DataTable columns={CLIENT_TABLE_FIELDS} rows={[]} />
    </>
  );
};

export default AllClients;
