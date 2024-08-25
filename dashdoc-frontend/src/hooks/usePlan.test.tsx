import 'jest-styled-components';
import { act, renderHook } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { usePlan } from './usePlan';
import MockAdapter from 'axios-mock-adapter';
import axios from 'axios';
import { mockPlansResponse } from '@tests/mocks/mockData';

jest.mock('axios');

beforeEach(() => {
  jest.clearAllMocks();
});

describe('usePlan hook', () => {
  const axiosMock = new MockAdapter(axios);

  it('allows us to update current term', () => {
    // Arrange
    axiosMock.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);
    let term = 'monthly';

    // Act
    const { result } = renderHook(() => usePlan(), { wrapper });
    act(() => result.current.setCurrentTerm('yearly'));
    act(() => term = result.current.currentTerm);

    // Assert
    expect(term).toBe('yearly');
  });

  it('returns allPlans object', () => {
    // Arrange
    axiosMock.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);
    let allPlans = {};

    // Act
    const { result } = renderHook(() => usePlan(), { wrapper });
    act(() => result.current.setCurrentTerm('yearly'));
    act(() => allPlans = result.current.allPlans);

    // Assert
    expect(allPlans).toHaveProperty('soleProviderPlans');
    expect(allPlans).toHaveProperty('smallAgencyPlans');
    expect(allPlans).toHaveProperty('mediumAgencyPlans');
    expect(allPlans).toHaveProperty('largeAgencyPlans');
  });
});
