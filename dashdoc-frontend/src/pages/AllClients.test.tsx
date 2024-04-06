import React from "react";
import {waitFor, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { renderWithProvider } from "@tests/renderWithProps";
import AllClients from "./AllClients";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";

describe("AllClients Page", () => {
  const axiosMock = new MockAdapter(axios);

  it("Should render page", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, []);

    // Act
    renderWithProvider(<AllClients />, { route: '/clients' });
    var pageTitle = await waitFor(() => screen.findByText(/My Clients/));

    // Assert
    expect(pageTitle).toBeInTheDocument();
  });

  it("Shows empty rows message if no clients are returned", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, []);

    // Act
    renderWithProvider(<AllClients />, { route: '/clients' });
    var noRowsMessage = await waitFor(() => screen.findByText(/No Clients/));

    // Assert
    expect(noRowsMessage).toBeInTheDocument();
  });
});