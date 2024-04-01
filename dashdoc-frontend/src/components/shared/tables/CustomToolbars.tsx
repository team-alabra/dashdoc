import React from "react";
import { GridToolbarFilterButton } from "@mui/x-data-grid"
import styled from "styled-components";

export const ClientsPageToolbar = () => {
  return (
    <StyledToolbar className="plain-toolbar">
      <GridToolbarFilterButton />
    </StyledToolbar>
  )
};

export const StyledToolbar = styled.div`
  height: 100px !important;
  width: 100%;
  background-color: lightgrey;
`;