import { renderHook, act, cleanup, waitFor } from "@testing-library/react";
import { useClient } from "./useClient";
import axios from "axios";
import { wrapper } from "@tests/renderWithProps";
import { mockClientsResponse, mockClientResponse } from "@tests/mocks/mockData";
import MockAdapter from "axios-mock-adapter";
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

  it("fetchProviderClients - Should fetch a list of clients on render", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    // Assert
    await waitFor(() =>
      expect(result.current.clients.length).toEqual(10)
    );
    
  });

  it("fetchProviderClients - Fails to fetch clients and throws error", async () => {
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

  it("fetchSingleClient - Should return method that can handle loading a single client", async () => {
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

  it("fetchSingleClient - Fails to load single client and throws", async () => {
    // Arrange
    axiosMock.onGet("/api/user/clients").reply(200, mockClientsResponse);
    axiosMock.onGet(`/api/client/${1}`).reply(() => {
      throw Error("Error from the server");
    });

    // Act
    const { result } = renderHook(() => useClient(), { wrapper });

    await waitFor(() => result.current.fetchClient(1));

    // Assert
    expect(console.error).toHaveBeenCalledTimes(1);
  });
});
