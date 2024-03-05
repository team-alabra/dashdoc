/**
 * @jest-environment jsdom
 */
import React from 'react';
import 'jest-styled-components';
import { fireEvent } from '@testing-library/dom';
import Login from '../pages/Login';
import { renderWithProvider } from './renderWithProps';
import * as loginHook from '@hooks/useLogin';


describe('Login Component', () => {
  it('should render the component', () => {
    const result = renderWithProvider(<Login />, { route: '/login' });

    const componentDiv = result.getByTestId('auth-container');
    expect(componentDiv).toBeDefined();
  });

  it('should properly display appropriate text input by user', () => {
    // Arrange
    jest.spyOn(loginHook, 'useLogin').mockReturnValue({
      loginHandler: jest.fn(),
      error: 'error',
      setError: jest.fn(),
    });

    const res = renderWithProvider(<Login />, { route: '/login' });

    // Act
    const emailInput = res.getByTestId('email-input');
    const passwordInput = res.queryByTestId('password-input');
    const loginButton = res.getByTestId("login-button");

    fireEvent.change(emailInput, { target: { value: 'test@email.com' } });
    fireEvent.change(passwordInput, { target: { value: 'Dashdoc123!' } });
    fireEvent.click(loginButton);
    const emailValue = res.getByTestId('email-input').getAttribute('value');
    const passwordValue = res
      .getByTestId('password-input')
      .getAttribute('value');

    // Assert
    expect(emailValue).toBe('test@email.com');
    expect(passwordValue).toBe('Dashdoc123!');
  });
});
