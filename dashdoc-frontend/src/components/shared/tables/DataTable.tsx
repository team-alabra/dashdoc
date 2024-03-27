import React from "react";
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { defaultTableProps } from "@constants";

type TableProps = {
  columns: GridColDef[],
  rows: any,
  useCheckbox?: boolean
}

export const DataTable: React.FC<TableProps> = (props) => {
  const { rows, columns, useCheckbox = false } = props;

  return (
    <div style={{ height: 400, width: '100%' }}>
      <DataGrid
        rows={rows}
        columns={columns.map((col) => ({...defaultTableProps, ...col}), [])}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10, 20]}
        checkboxSelection = { useCheckbox }
        sx={{
          fontSize:"14px"
        }}
      />
    </div>
  )
}

