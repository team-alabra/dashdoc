import React from "react";
import { ClientsPageToolbar } from ".";
import { render, waitFor, screen } from "@testing-library/react";
import '@testing-library/jest-dom'
import { DataGrid } from "@mui/x-data-grid";

describe("CustomToolbars", () => {
  describe("ClientsPageToolbar", () => {
    it("Component should render", async () => {
      // Arrange
      // Act
      render(
        <DataGrid
          rows={[]}
          columns={[]}
          slots={{
            toolbar: ClientsPageToolbar,
          }}
        />
      );
      const component = await waitFor(() =>
        screen.getByTestId("client-action-toolbar")
      );

      // Assert
      expect(component).toBeInTheDocument();
    });

    it("Component Should show an Add user button", async () => {
      // Arrange
      // Act
      render(
        <DataGrid
          rows={[]}
          columns={[]}
          slots={{
            toolbar: ClientsPageToolbar,
          }}
        />
      );
      const buttonText = await waitFor(() => screen.getByText(/Add Client/));

      // Assert
      expect(buttonText).toBeInTheDocument();
    });
  });
});
