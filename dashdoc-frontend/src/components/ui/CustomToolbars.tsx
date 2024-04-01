import React from "react";
import {
  GridToolbarFilterButton,
  GridToolbarQuickFilter,
} from "@mui/x-data-grid";
import styled from "styled-components";
import Button from "@components/shared/buttons/Button";

export const ClientsPageToolbar = () => {
  return (
    <StyledToolbar className="plain-toolbar flex-row-between">
      <div>
        <GridToolbarQuickFilter className="quick-filter"/>
        <GridToolbarFilterButton />
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

  & #text {
    font-size: 18px;
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
    font-size: 18px;
  }
`;
