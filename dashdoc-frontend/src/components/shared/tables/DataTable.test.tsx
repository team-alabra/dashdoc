import React from "react";
import { ClientsPageToolbar } from "../../ui";
import { render, waitFor, screen } from "@testing-library/react";
import '@testing-library/jest-dom'
import { DataTable } from "./DataTable";
import { mockClientsResponse } from "@tests/mocks/mockData";
import { CLIENT_TABLE_FIELDS } from "@constants";
import { GridColDef } from "@mui/x-data-grid";

describe("DataTable Component", () => {
  it("Should render component", () => {
    // Arrange
    // Act
    render(
      <DataTable
        rows={[]}
        columns={[]}
      />
    );
    const component = screen.getByTestId("base-table");

    // Assert
    expect(component).toBeInTheDocument();
  })

  it("Should Show a custom toolbar if one is given", () => {
    // Arrange
    // Act
    render(
      <DataTable
        rows={[]}
        columns={[]}
        toolbar={ClientsPageToolbar}
      />
    );
    
    const toolBarElement = screen.getAllByTestId("client-action-toolbar");

    // Assert
    expect(toolBarElement).toBeInTheDocument();
  })

  it("Should not Show a custom toolbar if none is given", () => {
    // Arrange

    // Act
    render(
      <DataTable
        rows={[]}
        columns={[]}
      />
    );
    const toolBarElement = screen.getAllByTestId("client-action-toolbar");

    // Assert
    expect(toolBarElement).not.toBeInTheDocument();
  })

  it("Should render rows and columns based on the data given", async () => {
    // Arrange
    const randomFields: GridColDef[] = [
      { field: "firstName", headerName: "First name" },
      { field: "lastName", headerName: "Last name" },
    ];

    const mockRandomData = [
      {
        id: 1,
        firstName: 'Testy1',
        lastName: 'Testerson',
      },
      {
        id: 2,
        firstName: 'Testy2',
        lastName: 'Testerson',
      }
    ];

    // Act
    <DataTable
      rows={mockRandomData}
      columns={randomFields}
    />

    const name1 = await waitFor(() => screen.getByText(/Testy1/));
    const name2 = await waitFor(() => screen.getByText(/Testy2/));

    // Assert
    expect(name1).toBeInTheDocument();
    expect(name2).toBeInTheDocument();
  })
})