import React from "react";
import "jest-styled-components";
import { fireEvent, waitFor } from "@testing-library/dom";
import { screen, act } from "@testing-library/react";
import SubscriptionPlans from "./SubscriptionPlans";
import { mockPlansResponse } from "@tests/mocks/mockData";
import { renderWithProvider } from "@tests/renderWithProps";
import axios from "axios";
import MockAdapter from 'axios-mock-adapter';

beforeEach(() => {
  jest.clearAllMocks();
});

describe("SubscriptionPlans page", () => {
  const axiosMock = new MockAdapter(axios);

  it("Should render the page", async () => {
    // Arrange
    axiosMock.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);

    // Act
    act(() => {
      renderWithProvider(<SubscriptionPlans />, {
        route: "/subscriptionPlans",
      });
    });

    const component = await waitFor(() => screen.getByTestId("subscription-home"));

    // Assert
    expect(component).toBeDefined();
  });

  it("Should render 4 pricing cards", async () => {
    // Arrange
    axiosMock.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);

    // Act
    act(() => {
      renderWithProvider(<SubscriptionPlans />, {
        route: "/subscriptionPlans",
      });
    });

    const components = await waitFor(() => screen.getAllByTestId("pricing-card"));

    // Assert
    expect(components.length).toEqual(4);
  });

  it("Should render plan term toggle", async () => {
    // Arrange
    axiosMock.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);

    // Act
    act(() => {
      renderWithProvider(<SubscriptionPlans />, {
        route: "/subscriptionPlans",
      });
    });

    const component = await waitFor(() => screen.getByTestId("plan-term-slider"));

    // Assert
    expect(component).toBeDefined();
  });

  it("Changes plan price based on toggle", async () => {
    // Arrange
    axiosMock.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);

    // Act
    act(() => {
      renderWithProvider(<SubscriptionPlans />, {
        route: "/subscriptionPlans",
      });
    });

    const annualButton = screen.getByText(/Annual/i);
    const monthlyPriceValues = screen.getAllByTestId("plan-price");

    // Assert
    expect(monthlyPriceValues[0].innerHTML).toEqual("$30");

    // Test Update
    await act(() => fireEvent.click(annualButton));

    const yearlyPriceValues = await waitFor(() => screen.getAllByTestId("plan-price"));

    // Assert again
    expect(yearlyPriceValues[0].innerHTML).toEqual("$250");
  });
});
