import { renderHook, act, cleanup, waitFor } from "@testing-library/react";
import { useClient } from "./useClient";
import axios from "axios";
import { wrapper } from "@tests/renderWithProps";
import { mockClientsResponse, mockClientResponse } from "@tests/mocks/mockData";
import MockAdapter from "axios-mock-adapter";
import { Client } from "@typings/client";
const axiosMock = new MockAdapter(axios);

beforeEach(() => {
  jest.clearAllMocks();
});

afterEach(() => {
  cleanup();
  axiosMock.reset();
});

describe("useClient hook", () => {
  console.error = jest.fn();

  it("Should render hook and return elements", () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    // Assert
    expect(result.current.clients).toBeDefined();
    expect(result.current.singleClient).toBeDefined();
    expect(result.current.fetchClient).toBeDefined();
    expect(result.current.isLoading).toBeDefined();
    expect(result.current.updateClient).toBeDefined();
    expect(result.current.addClient).toBeDefined();
  });

  it("fetchAllClients - Should fetch a list of clients on render", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    // Assert
    await waitFor(() => expect(result.current.clients.length).toEqual(10));
  });

  it("fetchAllClients - Fails to fetch clients and throws error", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(400, () => {
      throw Error("Error occurred fetching provider clients");
    });

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });
    await waitFor(() => result.current.clients);

    // Assert
    expect(console.error).toHaveBeenCalledTimes(1);
  });

  it("fetchClient - Gets client from API and adds it to store.", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);
    axiosMock.onGet(`/api/client/${1}`).reply(200, mockClientResponse);

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    await waitFor(() => result.current.fetchClient(1));

    // Assert
    expect(result.current.singleClient).toBeDefined();
    expect(result.current.singleClient.id).toEqual(1);
  });

  it("fetchClient - Fails to load single client and throws", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);
    axiosMock.onGet(`/api/client/${1}`).reply(() => {
      throw Error("Error from the server");
    });

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    await waitFor(() => result.current.fetchClient(1));

    // Assert
    expect(console.error).toHaveBeenCalled();
  });

  it("updateClient - Updates client and updates redux store", async () => {
    // Arrange
    const clientToUpdate: Client = {
      ...mockClientResponse,
      phoneNUmber: "(111)222-3333",
    };
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);
    axiosMock.onPut("/api/client/update").reply(200, clientToUpdate);

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    await waitFor(() => result.current.updateClient(clientToUpdate));

    // Assert
    expect(result.current.singleClient).toBeDefined();
    expect(result.current.singleClient.phoneNUmber).toEqual("(111)222-3333");
  });

  it("updateClient - Failed call. Client is not updated in redux.", async () => {
    // Arrange
    const clientToUpdate: Client = {
      ...mockClientResponse,
      phoneNUmber: "(111)222-3333",
    };
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);
    axiosMock.onGet("/api/client/update").reply(() => {
      throw Error("Error from the server");
    });

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    await waitFor(() => result.current.updateClient(clientToUpdate));

    // Assert
    expect(console.error).toHaveBeenCalled();
  });

  it("addClient - Adds a new provider client and updates client list in redux", async () => {
    // Arrange
    const clientToAdd: Client = { ...mockClientResponse, id: 20 };
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);
    axiosMock.onPost("/api/client/save").reply(200, clientToAdd);

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    await waitFor(() => result.current.addClient(clientToAdd));

    // Assert
    expect(result.current.clients.length).toEqual(11);
  });

  it("addClient - Failed API call. Client list is not updated.", async () => {
    // Arrange
    const clientToAdd: Client = { ...mockClientResponse, id: 20 };
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);
    axiosMock.onPost("/api/client/save").reply(() => {
      throw Error("Error from the server");
    });

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    await waitFor(() => result.current.addClient(clientToAdd));

    // Assert
    expect(console.error).toHaveBeenCalled();
  });
});
