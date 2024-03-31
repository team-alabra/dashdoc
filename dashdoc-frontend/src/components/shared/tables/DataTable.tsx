import React from "react";
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { DataGridPro } from '@mui/x-data-grid-pro';
import { defaultTableProps } from "@constants";
import { CustomNoRowsOverlay } from "@styles/dataTable";
import * as S from '@styles';

type TableProps = {
  columns: GridColDef[],
  rows: any,
  useCheckbox?: boolean
  noRowsLabel?: string
}

export const DataTable: React.FC<TableProps> = (props) => {
  const { rows, columns, useCheckbox = false, noRowsLabel } = props;

  return (
    <S.TableContainer>
      <DataGrid
        rows={rows}
        columns={columns.map((col) => ({...defaultTableProps, ...col}), [])}
        slots={{
          noRowsOverlay: () => CustomNoRowsOverlay(noRowsLabel)
        }}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10, 20]}
        checkboxSelection = { useCheckbox }
        disableRowSelectionOnClick
        sx={{
          fontSize:"13px",
        }}
      />
    </S.TableContainer>
  )
}