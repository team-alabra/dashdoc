import { renderHook, act } from '@testing-library/react';
import { useLogin } from './useLogin';
import axios from 'axios';
import { wrapper } from '@tests/renderWithProps';
import { mockUserResponse } from '@tests/mocks/mockData';
import { User } from '@typings/user';

jest.mock('axios');

beforeEach(() => {
  jest.clearAllMocks();
});

describe('useLogin hook', () => {
  const mockResponse = {
    data: mockUserResponse,
    status: 200,
    statusText: 'ok',
  };

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
    const axiosSpy = jest.spyOn(axios, 'post').mockResolvedValue(mockResponse);
    const { result } = renderHook(() => useLogin(), { wrapper });

    //Act
    await act(() =>
      result.current.loginHandler('test@email.com', 'Dashdoc123!')
    );

    // Assert
    expect(axiosSpy).toBeCalled();
    expect(window.location.href).toBe('http://localhost/');
  });

  it('login handler makes API request and no user is found', async () => {
    // Arrange
    const axiosSpy = jest
      .spyOn(axios, 'post')
      .mockResolvedValue(mockResponseNoUser);
    const { result } = renderHook(() => useLogin(), { wrapper });

    //Act
    await act(() =>
      result.current.loginHandler('test@email.com', 'Dashdoc123!')
    );
    console.error = jest.fn();

    // Assert
    expect(axiosSpy).toBeCalled();
    expect(console.error).not.toBeCalled();
  });
});
