/**
 * @jest-environment jsdom
 */
import 'jest-styled-components';
import { act, renderHook } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { useAuth } from './useAuth';
import axios from 'axios';

jest.mock('axios');

beforeEach(() => {
  jest.clearAllMocks();
});

describe('useAuth hook', () => {
  it('should return isAuthenticated function', () => {
    const { result } = renderHook(() => useAuth(), { wrapper });
    expect(result.current.isAuthenticated).toBeDefined();
  });

  it('should validate an authenticated user', async () => {
    const axiosSpy = jest
      .spyOn(axios, 'get')
      .mockResolvedValue({ email: 'test@email.com', valid: true });
    const { result } = renderHook(() => useAuth(), { wrapper });

    await act(() => result.current.isAuthenticated());

    expect(axiosSpy).toBeCalled();

    expect(result.current.isValid).toBe(true);
  });

  it('should invalidate a user with invalid/expired credentials', async () => {
    const axiosSpy = jest.spyOn(axios, 'get').mockRejectedValue({
      status: 404,
      data: { email: 'test@email.com', valid: false },
    });

    const { result } = renderHook(() => useAuth(), { wrapper });
    await act(() => result.current.isAuthenticated());

    expect(axiosSpy).toBeCalled();
    expect(result.current.isValid).toBe(false);
  });

  it('should invalidate a user who has not signed in', async () => {
    const axiosSpy = jest
      .spyOn(axios, 'get')
      .mockRejectedValue({ status: 400, data: { email: null, valid: null } });

    const { result } = renderHook(() => useAuth(), { wrapper });
    await act(() => result.current.isAuthenticated());

    expect(axiosSpy).toBeCalled();
    expect(result.current.isValid).toBe(false);
  });
});
