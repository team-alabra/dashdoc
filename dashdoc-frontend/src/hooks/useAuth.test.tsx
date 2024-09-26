import 'jest-styled-components';
import { act, renderHook, waitFor } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { useAuth } from './useAuth';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

beforeEach(() => {
  jest.clearAllMocks();
});

describe('useAuth hook', () => {
  const mockAxios = new MockAdapter(axios);

  it('should return isAuthenticated function', () => {
    const { result } = renderHook(() => useAuth(), { wrapper });
    expect(result.current.isAuthenticated).toBeDefined();
  });

  it('should validate an authenticated user', async () => {
    mockAxios.onGet('/api/auth/validateUser').reply(200, { email: 'test@email.com', valid: true });

    const { result } = renderHook(() => useAuth(), { wrapper });

    await act(() => result.current.isAuthenticated());

    await waitFor(() => 
      expect(result.current.isValidUser).toBe(true)
    );
  });

  it('should invalidate a user with invalid/expired credentials', async () => {
    // TODO - Verify in the future backend if we should return a 200 with a valid: false.
    mockAxios.onGet('/api/auth/validateUser').reply(404, { email: 'test@email.com', valid: false });
    const { result } = renderHook(() => useAuth(), { wrapper });
    await act(() => result.current.isAuthenticated());

    expect(result.current.isValidUser).toBe(false);
  });

  it('should invalidate a user who has not signed in', async () => {
    mockAxios.onGet('/api/auth/validateUser').reply(400, null);

    const { result } = renderHook(() => useAuth(), { wrapper });
    await act(() => result.current.isAuthenticated());

    await waitFor(() => 
      expect(result.current.isValidUser).toBe(false)
    );
  });
});
