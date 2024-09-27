import React from "react";
import {waitFor, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { renderWithProvider } from "@tests/renderWithProps";
import AllClients from "./AllClients";
import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import { mockClientResponse } from "@tests/mocks/mockData";
const axiosMock = new MockAdapter(axios);

afterAll(() => {
  axiosMock.restore();
});

describe("AllClients Page", () => {  
  it("Should render page", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, [mockClientResponse]);

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