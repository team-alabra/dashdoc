import 'jest-styled-components';
import { act, renderHook, cleanup } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { usePlan } from './usePlan';
import MockAdapter from 'axios-mock-adapter';
import axios from 'axios';
import { mockPlansResponse } from '@tests/mocks/mockData';
const mockAxios = new MockAdapter(axios);

afterEach(() => {
  cleanup();
  mockAxios.reset();
});

describe('usePlam hook', () => {
  it('allows us to update current term', () => {
    // Arrange
    mockAxios.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);
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
    mockAxios.onGet('/api/plan/all-plans').reply(200, mockPlansResponse);
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