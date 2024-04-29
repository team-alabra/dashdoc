/**
 * @jest-environment jsdom
 */
import 'jest-styled-components';
import { act, renderHook } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { useAnalytics } from './useAnalytics';
import axios from 'axios';
import { mockUserAnalytics } from '@utils/mocks/analyticsMocks';

jest.mock('axios');

beforeEach(() => {
  jest.clearAllMocks();
});

describe('useAnalytics hook', () => {
  it('should return analyticsHandler function', () => {
    const { result } = renderHook(() => useAnalytics(), { wrapper });
    expect(result.current.analyticsHandler).toBeDefined();
  });

  it('should return user`s appropriate analytics', async () => {
    const axiosSpy = jest
      .spyOn(axios, 'get')
      .mockResolvedValue(mockUserAnalytics);
    const { result } = renderHook(() => useAnalytics(), { wrapper });

    await act(() => result.current.analyticsHandler());

    expect(result.current.user_analytics).toBe(mockUserAnalytics);
  });
});
