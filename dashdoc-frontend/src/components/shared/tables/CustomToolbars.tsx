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
  height: 50px !important;
  width: 100%;
  background: none;
  border: none !important;

  & * {
    font-size: 14px;
    color: black;
  }
`;