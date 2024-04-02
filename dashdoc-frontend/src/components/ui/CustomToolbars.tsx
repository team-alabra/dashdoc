import React from "react";
import {
  GridToolbarFilterButton,
  GridToolbarQuickFilter,
} from "@mui/x-data-grid";
import styled from "styled-components";
import Button from "@components/shared/buttons/Button";

export const ClientsPageToolbar = () => {
  return (
    <StyledToolbar data-testid="client-action-toolbar" className="client-action-toolbar flex-row-between">
      <div className="flex-row align-center">
        <GridToolbarQuickFilter className="quick-filter"/>
        <GridToolbarFilterButton className="filter-button"/>
      </div>
      <Button
        className="add-client-button"
        variant="contained"
        color="success"
        size="small"
      >
        <span>Add Client</span>
      </Button>
    </StyledToolbar>
  );
};

export const StyledToolbar = styled.div`
  height: 2rem;
  width: 100%;
  background: none;
  border: none !important;
  margin-bottom: 10px;

  & * {
    font-size: 14px;
    color: black;
  }

  & .add-client-button * {
    color: white;
  }

  & .quick-filter > * {
    width: 400px;
    border: 1px solid rgba(224, 224, 224, 1);
    border-radius: 8px;
    font-size: 14px;
    padding: 2px 4px;
  }

  & .quick-filter svg {
    font-size: 21px;
  }

  & .filter-button {
    font-size: 14px;
    color: black;
  }

  & .filter-button svg {
    font-size: 21px;
    color: black;
    margin: 0;
    padding: 0;
    margin-left: 0.5rem;
  }
`;
