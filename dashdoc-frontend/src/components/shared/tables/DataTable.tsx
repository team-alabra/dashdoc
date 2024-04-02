import React from "react";
import {
  DataGrid,
  GridColDef,
} from "@mui/x-data-grid";

import { defaultTableProps } from "@constants";
import { CustomNoRowsOverlay } from "@styles/dataTable";
import * as S from "@styles";

type TableProps = {
  columns: GridColDef[];
  rows: any;
  useCheckbox?: boolean;
  noRowsLabel?: string;
  toolbar?: React.FC | null;
};

export const DataTable: React.FC<TableProps> = (props) => {
  const { rows, columns, useCheckbox = false, noRowsLabel, toolbar = null } = props;

  return (
    <S.TableContainer data-testid="base-table">
      <DataGrid
        rows={rows}
        columns={columns.map((col) => ({ ...defaultTableProps, ...col }), [])}
        slots={{
          noRowsOverlay: () => CustomNoRowsOverlay(noRowsLabel),
          toolbar: toolbar,
        }}
        slotProps={{
          toolbar: {
            showQuickFilter: true,
          },
        }}
        filterModel={{
          items: [
            { field: "lastName", operator: "contains" },
          ],
        }}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10, 20]}
        checkboxSelection={useCheckbox}
        disableRowSelectionOnClick
        sx={{
          fontSize: "13px",
        }}
      />
    </S.TableContainer>
  );
};