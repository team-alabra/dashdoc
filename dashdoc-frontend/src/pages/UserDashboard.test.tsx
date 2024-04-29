import React from 'react';
import 'jest-styled-components';
import { fireEvent, waitFor } from '@testing-library/dom';
import { screen, act } from '@testing-library/react';
import { SignUp } from './SignUp';
import { renderWithProvider } from '@tests/renderWithProps';
import axios from 'axios';
import UserDashboard from './UserDashboard';


beforeEach(() => {
  jest.clearAllMocks();
});

describe('User Dashboard Page', () => {
  it('should render the page', () => {
    act(() => {
      renderWithProvider(<UserDashboard />, {
        route: '/dashboard',
      });
    });

    const component = waitFor(() => {
      screen.getByTestId('dashboard-container');
    });

    expect(component).toBeDefined();
  });

  it.skip('renders the appropriate input label, based on customer selecting their user type', () => {
    act(() => {
      renderWithProvider(<SignUp />, {
        route: '/signup',
      });
    });

    const agencyProviderButton: HTMLInputElement =
      screen.getByLabelText(/Agency Provider/);

    expect(agencyProviderButton.checked).toEqual(false);

    waitFor(() => fireEvent.click(agencyProviderButton));

    expect(agencyProviderButton.checked).toEqual(true);

    expect(screen.getAllByLabelText(/Agency Code/)).toBeTruthy();
    expect(screen.queryByText("Agency Name")).toBeNull();
  });
});
