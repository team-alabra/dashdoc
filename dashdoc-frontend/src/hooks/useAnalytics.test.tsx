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
    expect(result.current.analytics).toBeDefined();
  });

  it('should return user`s appropriate analytics', async () => {
    jest.spyOn(axios, 'get').mockResolvedValue(mockUserAnalytics);
    const { result } = renderHook(() => useAnalytics(), { wrapper });

    // this is mocking analytics from state*
    await act(() => result.current.analytics);

    expect(result.current.analytics.earnings).toBe(1153);
    expect(result.current.analytics.num_of_notes.submitted).toBe(10);
  });
});
