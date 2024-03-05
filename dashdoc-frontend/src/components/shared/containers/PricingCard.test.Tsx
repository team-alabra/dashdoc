import React from "react";
import "jest-styled-components";
import { render, screen } from "@testing-library/react";
import { PricingCard } from "./PricingCard";
import { mockPlansResponse } from "../../../tests/mocks/mockData";
import * as plansHook from "../../../services/hooks/usePlan";
import { PricingTheme } from "../../../typings/pricing";

describe("PricingCard component", () => {
  it("Should render the component", () => {
    // Arrange/Act
    render(
      <PricingCard
        id="1"
        theme={PricingTheme.bright_blue}
        font_color="blue"
        planDetail={mockPlansResponse.soleProviderPlans.monthly}
        term="monthly"
        imgPath="fake/image/path"
      />
    );

    const componentDiv = screen.getByTestId("pricing-card");
    
    // Assert
    expect(componentDiv).toBeDefined();
  });

  it("Renders price based on state", () => {
    // Arrange/Act
    render(
      <PricingCard
        id="1"
        theme={PricingTheme.bright_blue}
        font_color="blue"
        planDetail={mockPlansResponse.soleProviderPlans.monthly}
        term="monthly"
        imgPath="fake/image/path"
      />
    );

    const componentDiv = screen.getByText("$30");
    
    // Assert
    expect(componentDiv).toBeDefined();
  });

  it("Shows plan feature list", () => {
    // Arrange/Act
    render(
      <PricingCard
        id="1"
        theme={PricingTheme.bright_blue}
        font_color="blue"
        planDetail={mockPlansResponse.soleProviderPlans.monthly}
        term="monthly"
        imgPath="fake/image/path"
      />
    );

    const feature1 = screen.getByText("Billing platform and invoicing");
    const feature2 = screen.getByText("Document generation");
    
    // Assert
    expect(feature1).toBeDefined();
    expect(feature2).toBeDefined();
  });

  it("Displays Start Free Trial button", () => {
    // Arrange/Act
    render(
      <PricingCard
        id="1"
        theme={PricingTheme.bright_blue}
        font_color="blue"
        planDetail={mockPlansResponse.soleProviderPlans.monthly}
        term="monthly"
        imgPath="fake/image/path"
      />
    );

    const componentDiv = screen.getByTestId("plan-button");
    
    // Assert
    expect(componentDiv).toBeDefined();
  });
});
