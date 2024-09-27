import React from 'react';
import 'jest-styled-components';
import { waitFor } from '@testing-library/dom';
import { screen, act } from '@testing-library/react';
import { renderWithProvider } from '@tests/renderWithProps';
import axios from 'axios';
import UserDashboard from './UserDashboard';
import { SOLE_PROVIDER_RESPONSE } from '@utils/mocks/signupMocks';

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

  it('renders the appropriate analytics, based on user type', () => {
    act(() => {
      renderWithProvider(<UserDashboard />, {
        route: '/dashboard',
      });
    });

    // Arrange
    const axiosSpy = jest
      .spyOn(axios, 'get')
      .mockResolvedValue(SOLE_PROVIDER_RESPONSE);

    const label1 = waitFor(() => screen.getByTestId('completed-notes-metric'));
    expect(label1).toBeDefined();
  });
});
