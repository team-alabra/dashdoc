import { renderHook, act } from '@testing-library/react';
import { useLogin } from './useLogin';
import axios from 'axios';
import { wrapper } from '@tests/renderWithProps';
import { mockUserResponse } from '@tests/mocks/mockData';
import { User } from '@typings/user';
import MockAdapter from 'axios-mock-adapter';

beforeEach(() => {
  jest.clearAllMocks();
});

describe('useLogin hook', () => {
  const mockAxios = new MockAdapter(axios);
  const mockResponseNoUser = {
    data: null as User,
    status: 404,
    statusText: 'Not Found',
  };

  it('should return on-submit handler', () => {
    const { result } = renderHook(() => useLogin(), { wrapper });
    expect(result.current.loginHandler).toBeDefined();
  });

  it('login handler makes a successful API request and returns user', async () => {
    // Arrange
    mockAxios.onPost('/api/auth/signin').reply(200, mockUserResponse);
    
    const { result } = renderHook(() => useLogin(), { wrapper });

    //Act
    await act(() =>
      result.current.loginHandler('test@email.com', 'Dashdoc123!')
    );

    // Assert
    expect(window.location.href).toBe('http://localhost/dashboard');
  });

  it('login handler makes API request and no user is found', async () => {
    // Arrange
    mockAxios.onPost('/api/auth/signin').reply(200, null);
    const { result } = renderHook(() => useLogin(), { wrapper });

    //Act
    await act(() =>
      result.current.loginHandler('test@email.com', 'Dashdoc123!')
    );
    console.error = jest.fn();

    // Assert
    expect(console.error).not.toBeCalled();
  });
});
