/*
 * @jest-environment jsdom
*/
import 'jest-styled-components';
import { act, cleanup, renderHook, waitFor } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { useAnalytics } from './useAnalytics';
import axios from 'axios';
import { mockUserAnalytics } from '@utils/mocks/analyticsMocks';
import MockAdapter from 'axios-mock-adapter';
const mockAxios = new MockAdapter(axios);

beforeEach(() => {
  jest.clearAllMocks();
});

afterEach(() => {
  cleanup();
  mockAxios.reset();
});

describe('useAnalytics hook', () => {
  const data = {...mockUserAnalytics, lastUpdated: new Date().toString()} 

  it('should return analyticsHandler function', () => {
    const { result } = renderHook(() => useAnalytics(), { wrapper });
    expect(result.current.analyticsHandler).toBeDefined();
  });

  it('should return user`s appropriate analytics', async () => {
    mockAxios.onGet('/api/user/analytics').reply(200, data)

    const { result } = renderHook(useAnalytics);

    await act(() => result.current.analyticsHandler())

    await waitFor(() =>
      expect(result.current.userAnalytics).toEqual(data)
    );
  });
});