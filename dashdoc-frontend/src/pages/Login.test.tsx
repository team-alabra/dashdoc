import React from 'react';
import 'jest-styled-components';
import { fireEvent, waitFor } from '@testing-library/dom';
import { screen, act, renderHook } from '@testing-library/react';
import { renderWithProvider } from '@tests/renderWithProps';
import Login from './Login';
import axios from 'axios';
import { useLogin } from '@hooks';
import { wrapper } from '@tests/renderWithProps';

jest.mock('axios');

beforeEach(() => {
  jest.clearAllMocks();
});

describe('Login Page', () => {
  it('Should render the page', async () => {
    // Act
    act(() => {
      renderWithProvider(<Login />, {
        route: '/login',
      });
    });

    const component = await waitFor(() => screen.getByTestId('auth-container'));

    // Assert
    expect(component).toBeDefined();
  });

  it('should display appropriate error message if an unauthorized user is attempting to login', async () => {
    // Arrange
    const axiosSpy = jest.spyOn(axios, 'post').mockRejectedValue({
      data: null,
      status: 401,
      statusText: 'Unauthorized',
    });
    const { result } = renderHook(() => useLogin(), { wrapper });

    //Act
    act(() => {
      renderWithProvider(<Login />, {
        route: '/login',
      });
    });

    await act(() => result.current.loginHandler('you@test.com', '123'));
    console.error = jest.fn();

    // Test Login
    const loginButton = screen.getByTestId('login-button');
    await act(() => fireEvent.click(loginButton));

    //Assert
    expect(axiosSpy).toBeCalled();

    const component = waitFor(() => screen.getByTestId('error-message'));
    expect(component).toBeDefined();
  });
});
